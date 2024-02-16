import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/models/order/IOrder';
import { Status } from 'src/app/models/order/IStatus';
import { ToastService } from 'src/app/services/common/toast.service';
import { OrderService } from 'src/app/services/order/order.service';
import { StatusService } from 'src/app/services/order/status.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css'],
})
export class OrdersListComponent implements OnInit {
  orderList: Order[] = [];
  filteredOrders: Order[] = [];
  statusList: Status[] = [];
  searchTerm: string = '';
  issueDateSort: string = 'Default';
  deliveryDateSort: string = 'Default';
  orderToDeleteId: number | null = null;
  deleteMessage: string = '';
  statusFilter: string = 'Estado';
  isLoading: boolean = true;

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  constructor(
    private router: Router,
    private orderService: OrderService,
    private toastService: ToastService,
    private statusService: StatusService
  ) {}

  ngOnInit() {
    this.getOrders();
    this.getStatuses();
  }

  getOrders() {
    this.orderService.getOrders().subscribe((res) => {
      this.orderList = res;
      this.filterOrders();
      this.updateTotalPages();

      setTimeout(() => {
        this.isLoading = false;
      }, 500);
    });
  }

  getStatuses() {
    this.statusService.getStatuses().subscribe((res) => {
      this.statusList = res;
    });
  }

  addOrder() {
    this.router.navigate(['/orders/add']);
  }

  openOrder(id: number) {
    this.router.navigate([`/orders/${id}`]);
  }

  editOrder(id: number) {
    this.router.navigate([`/orders/edit/${id}`]);
  }

  confirmDelete(id: number, number: string, supplier: string) {
    this.deleteMessage = `<div>¿Está seguro de que desea cancelar la orden?</div>
    <div class="mt-2">${number} <small>(Proveedor: ${supplier})</small></div>`;
    this.orderToDeleteId = id;
  }

  deleteOrder() {
    if (this.orderToDeleteId) {
      this.orderService.cancelOrder(this.orderToDeleteId).subscribe((res) => {
        this.getOrders();
        this.orderToDeleteId = null;
        this.toastService.showSuccessToast('Orden cancelada correctamente!');
      });
    }
  }

  filterOrders() {
    this.currentPage = 1;
    this.filteredOrders = [...this.orderList];

    // Status Filter
    if (this.statusFilter !== 'Estado') {
      this.filteredOrders = this.filteredOrders.filter((item) => {
        if (item.status?.name) {
          return item.status.name
            .toLowerCase()
            .includes(this.statusFilter.toLowerCase());
        }
        return false;
      });
    }

    // Search Filter
    if (this.searchTerm) {
      this.filteredOrders = this.filteredOrders.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );
    }

    // Issue Date Sort
    if (this.issueDateSort === 'Ascending') {
      this.filteredOrders.sort(
        (a, b) =>
          new Date(a.issueDate!).getTime() - new Date(b.issueDate!).getTime()
      );
    } else if (this.issueDateSort === 'Descending') {
      this.filteredOrders.sort(
        (a, b) =>
          new Date(b.issueDate!).getTime() - new Date(a.issueDate!).getTime()
      );
    }

    // Delivery Date Sort
    if (this.deliveryDateSort === 'Ascending') {
      this.filteredOrders.sort(
        (a, b) =>
          new Date(a.deliveryDate!).getTime() -
          new Date(b.deliveryDate!).getTime()
      );
    } else if (this.deliveryDateSort === 'Descending') {
      this.filteredOrders.sort(
        (a, b) =>
          new Date(b.deliveryDate!).getTime() -
          new Date(a.deliveryDate!).getTime()
      );
    }

    this.updateTotalPages();
  }

  updateIssueDateSort() {
    switch (this.issueDateSort) {
      case 'Default':
        this.issueDateSort = 'Ascending';
        break;
      case 'Ascending':
        this.issueDateSort = 'Descending';
        break;
      default:
        this.issueDateSort = 'Default';
        break;
    }

    this.deliveryDateSort = 'Default';
    this.filterOrders();
  }

  updateDeliveryDateSort() {
    switch (this.deliveryDateSort) {
      case 'Default':
        this.deliveryDateSort = 'Ascending';
        break;
      case 'Ascending':
        this.deliveryDateSort = 'Descending';
        break;
      default:
        this.deliveryDateSort = 'Default';
        break;
    }

    this.issueDateSort = 'Default';
    this.filterOrders();
  }

  updatePage(navigation: 'prev' | 'next' | 'first' | 'last') {
    switch (navigation) {
      case 'prev':
        if (this.currentPage > 1) {
          this.currentPage--;
        }
        break;
      case 'next':
        if (this.hasMoreItems()) {
          this.currentPage++;
        }
        break;
      case 'first':
        this.currentPage = 1;
        break;
      case 'last':
        this.currentPage = this.totalPages;
        break;
    }
  }

  updateTotalPages() {
    const minPages = 1;

    this.totalPages = Math.max(
      minPages,
      Math.ceil(this.filteredOrders.length / this.itemsPerPage)
    );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredOrders.length;
  }

  calculateRange(): string {
    const start = (this.currentPage - 1) * this.itemsPerPage + 1;
    const end = Math.min(
      this.currentPage * this.itemsPerPage,
      this.filteredOrders.length
    );
    return `Mostrando ${start} - ${end} de ${this.filteredOrders.length} ordenes`;
  }

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'Cancelado':
        return 'badge text-bg-danger';
      case 'En Proceso':
        return 'badge text-bg-secondary';
      case 'En Camino':
        return 'badge text-bg-primary';
      case 'Entregado':
        return 'badge text-bg-success';
      default:
        return 'badge text-bg-secondary';
    }
  }

  updateFilter(status: string) {
    this.statusFilter = status;
    this.filterOrders();
  }
}

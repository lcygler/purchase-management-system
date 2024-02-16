import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { faCalendar } from '@fortawesome/free-regular-svg-icons';

import { Order } from 'src/app/models/order/IOrder';
import { OrderDetail } from 'src/app/models/order/IOrderDetail';
import { Status } from 'src/app/models/order/IStatus';
import { Category } from 'src/app/models/product/ICategory';
import { Product } from 'src/app/models/product/IProduct';
import { Supplier } from 'src/app/models/supplier/ISupplier';

import { ToastService } from 'src/app/services/common/toast.service';
import { OrderDetailService } from 'src/app/services/order/order-detail.service';
import { OrderService } from 'src/app/services/order/order.service';
import { StatusService } from 'src/app/services/order/status.service';
import { ProductService } from 'src/app/services/product/product.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-orders-form',
  templateUrl: './orders-form.component.html',
  styleUrls: ['./orders-form.component.css'],
})
export class OrdersFormComponent implements OnInit {
  order: Order = {
    number: '',
    issueDate: null,
    deliveryDate: null,
    comments: '',
    total: null,
    status: {},
    supplier: {},
  };

  orderId: number | null = null;
  orderNumber: string | null = null;
  nextOrderId: number | null = null;
  isAddView: boolean = false;
  isEditView: boolean = false;
  isSupplierSelectDisabled: boolean = false;
  todayDate: string = '';

  orderList: Order[] = [];
  statusList: Status[] = [];
  supplierList: Supplier[] = [];
  productList: Product[] = [];
  categoryList: Category[] = [];
  orderDetailList: OrderDetail[] = [];
  filteredProducts: Product[] = [];

  selectedSupplierId: number | null = null;
  selectedProductId: number | null = null;
  selectedQuantity: number | null = null;
  productToDeleteId: number | null = null;
  selectedProductPrice: number | null = null;
  supplierImageUrl: string = '';
  productImageUrl: string = '';
  deleteMessage: string = '';

  faCalendar = faCalendar;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private location: Location,
    private orderService: OrderService,
    private supplierService: SupplierService,
    private productService: ProductService,
    private toastService: ToastService,
    private orderDetailService: OrderDetailService,
    private statusService: StatusService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('orderId');

    if (id) {
      this.orderId = parseInt(id);
      this.getOrderById(this.orderId);
      this.getOrderDetails(this.orderId);
      this.getStatuses();
    } else {
      this.order.status!.name = 'En Proceso';
    }

    this.todayDate = this.getTodayDate();
    this.isAddView = this.isAddRoute();
    this.isEditView = this.isEditRoute();

    if (this.isAddView) {
      this.getOrderNumber();
    }

    this.getSuppliers();
    this.getProducts();
    this.getOrders();
  }

  getOrderById(id: number) {
    if (id) {
      this.orderService.getOrderById(id).subscribe({
        next: (res) => {
          this.order = res;
          this.orderNumber = res.number || null;
          this.order.deliveryDate = String(res.deliveryDate!).split('T')[0];
        },
        error: (error) => {
          if (error.status === 404) {
            this.router.navigate(['/not-found']);
          }
        },
      });
    }
  }

  getOrderDetails(id: number) {
    if (id) {
      this.orderService.getOrderDetails(id).subscribe((res) => {
        this.orderDetailList = res;
      });
    }
  }

  getOrders() {
    this.orderService.getOrders().subscribe((res) => {
      this.orderList = res;
    });
  }

  getOrderNumber() {
    this.orderService.getOrderNumber().subscribe((res) => {
      this.order.number = res;
    });
  }

  getStatuses() {
    this.statusService.getStatuses().subscribe((res) => {
      this.statusList = res;
    });
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res.filter((s) => !s.isDeleted);
    });
  }

  getProducts() {
    this.productService.getProducts().subscribe((res) => {
      this.productList = res.filter(
        (p) => !p.supplier.isDeleted && !p.isDeleted
      );
    });
  }

  onSubmit(form: NgForm) {
    if (!this.isAddView && !this.isEditView) return;

    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    if (this.isAddView) {
      const newOrder: Order = {
        number: formData.number,
        issueDate: this.getTodayDate() + 'T00:00:00',
        deliveryDate: formData.deliveryDate + 'T00:00:00',
        comments: formData.comments.trim(),
        total: this.calculateTotal(),
        status: { id: 1 },
        supplier: { id: this.selectedSupplierId! }, // { id: formData.supplier }
      };

      // Add order
      this.orderService
        .createOrder(newOrder)
        .subscribe((createdOrder: Order) => {
          let orderDetails = [];

          for (const orderDetail of this.orderDetailList) {
            const newOrderDetail: OrderDetail = {
              order: { id: createdOrder.id },
              product: { id: orderDetail.product.id },
              quantity: orderDetail.quantity,
              price: orderDetail.product.price!,
            };

            orderDetails.push(newOrderDetail);
          }

          // Add order details
          this.orderDetailService
            .createOrderDetails(orderDetails)
            .subscribe((res) => {
              this.toastService.showSuccessToast(
                'Orden agregada correctamente!'
              );
              form.reset();
              this.navigateToOrders();
            });
        });
    } else {
      const updatedOrder: Order = {
        number: formData.number,
        issueDate: this.order.issueDate,
        deliveryDate: formData.deliveryDate + 'T00:00:00',
        comments: formData.comments.trim(),
        total: this.order.total,
        status: { id: formData.status },
        supplier: { id: this.order.supplier.id },
      };

      // Update order
      this.orderService
        .updateOrder(this.orderId!, updatedOrder)
        .subscribe((createdOrder: Order) => {
          this.toastService.showSuccessToast('Orden modificada correctamente!');
          this.navigateToOrders();
        });
    }
  }

  addProduct() {
    if (!this.selectedProductId) {
      return;
    }

    this.productService
      .getProductById(this.selectedProductId)
      .subscribe((product) => {
        const existingProduct = this.orderDetailList.find(
          (orderDetail) => orderDetail.product.id === product.id
        );

        if (existingProduct) {
          existingProduct.quantity += this.selectedQuantity!;

          this.toastService.showSuccessToast(
            'Cantidad actualizada correctamente!'
          );
        } else {
          const orderDetail: OrderDetail = {
            product: product,
            quantity: this.selectedQuantity!,
            price: product.price!,
          };

          this.orderDetailList.push(orderDetail);

          this.toastService.showSuccessToast(
            'Producto agregado correctamente!'
          );

          this.isSupplierSelectDisabled = true;
          this.selectedProductId = null;
          this.selectedQuantity = null;
          this.selectedProductPrice = null;
          this.productImageUrl = '';
        }
      });
  }

  confirmDelete(id: number) {
    this.deleteMessage = `¿Está seguro de que desea remover el producto #${id}?`;
    this.productToDeleteId = id;
  }

  removeProduct() {
    if (this.productToDeleteId) {
      const index = this.orderDetailList.findIndex(
        (orderDetail) => orderDetail.product.id === this.productToDeleteId
      );

      if (index !== -1) {
        this.orderDetailList.splice(index, 1);
        this.toastService.showSuccessToast('Producto removido correctamente!');
      } else {
        console.warn(
          `Producto con ID ${this.productToDeleteId} no encontrado.`
        );
      }

      this.deleteMessage = '';
      this.productToDeleteId = null;
    }
  }

  resetForm(form: NgForm) {
    this.selectedSupplierId = null;
    this.selectedProductId = null;
    this.selectedProductPrice = null;
    this.selectedQuantity = null;
    this.isSupplierSelectDisabled = false;
    this.orderDetailList = [];

    form.reset();
    form.control.markAsPristine();
    form.control.markAsUntouched();
    form.control.updateValueAndValidity();

    this.todayDate = this.getTodayDate();
    const formattedDate = this.formatDateAsc(this.todayDate);
    form.control.patchValue({ issueDate: formattedDate });
  }

  onSupplierChange(selectedSupplierId: number) {
    if (selectedSupplierId) {
      this.productService
        .getProductsBySupplier(selectedSupplierId)
        .subscribe((res) => {
          this.filteredProducts = res.filter(
            (p) => !p.supplier.isDeleted && !p.isDeleted
          );
        });

      const selectedSupplier = this.supplierList.find(
        (supplier) => supplier.id === Number(selectedSupplierId)
      );

      if (selectedSupplier) {
        this.supplierImageUrl = selectedSupplier.image?.url!;
      } else {
        this.supplierImageUrl = '';
      }
    } else {
      this.filteredProducts = [];
      this.supplierImageUrl = '';
    }

    this.productImageUrl = '';
    this.selectedProductPrice = null;
  }

  onProductChange(selectedProductId: number) {
    if (selectedProductId) {
      const selectedProduct = this.productList.find(
        (product) => product.id === Number(selectedProductId)
      );

      if (selectedProduct) {
        this.productImageUrl = selectedProduct.image?.url!;
        this.selectedProductPrice = selectedProduct.price;
      } else {
        this.productImageUrl = '';
        this.selectedProductPrice = null;
      }

      this.selectedQuantity = 1;
    } else {
      this.productImageUrl = '';
      this.selectedProductPrice = null;
    }
  }

  getTodayDate(): string {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    const day = ('0' + currentDate.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  getMinDate(): string {
    const minDays = 2;
    const currentDate = new Date(this.todayDate);
    currentDate.setDate(currentDate.getDate() + minDays);

    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    const day = ('0' + currentDate.getDate()).slice(-2);

    return `${year}-${month}-${day}`;
  }

  getMaxDate(): string {
    const maxDays = 365;
    const currentDate = new Date(this.todayDate);
    currentDate.setDate(currentDate.getDate() + maxDays);

    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    const day = ('0' + currentDate.getDate()).slice(-2);

    return `${year}-${month}-${day}`;
  }

  isDeliveryDateValid() {
    const deliveryDate = new Date(this.order.deliveryDate!);
    const minDate = new Date(this.getMinDate());
    const maxDate = new Date(this.getMaxDate());

    return deliveryDate >= minDate && deliveryDate <= maxDate;
  }

  calculateTotal(): number {
    let total = 0;

    for (const item of this.orderDetailList) {
      total += item.price * item.quantity;
    }

    return total;
  }

  formatDateDesc(dateString: string): string {
    const parts = dateString.split('-');
    if (parts.length === 3) {
      const [day, month, year] = parts;
      return `${year}-${month}-${day}`;
    } else {
      return dateString;
    }
  }

  formatDateAsc(dateString: string): string {
    const parts = dateString.split('-');
    if (parts.length === 3) {
      const [year, month, day] = parts;
      return `${day}/${month}/${year}`;
    } else {
      return dateString;
    }
  }

  navigateToOrders() {
    this.location.back();
    // this.router.navigate(['/orders']);
  }

  navigateBack() {
    this.location.back();
  }

  isAddRoute(): boolean {
    const route = this.router.url;
    return route.includes('/orders/add');
  }

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/orders/edit');
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'Cancelado':
        return 'order-cancelled';
      case 'En Proceso':
        return 'order-in-progress';
      case 'En Camino':
        return 'order-on-its-way';
      case 'Entregado':
        return 'order-delivered';
      default:
        return 'order-in-progress';
    }
  }

  isNumberValid(): boolean {
    return !this.orderList.some(
      (o) => o.number === this.order.number && o.id !== this.order.id
    );
  }

  editOrder() {
    this.router.navigate([`/orders/edit/${this.orderId}`]);
  }
}

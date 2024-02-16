import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faImage } from '@fortawesome/free-regular-svg-icons';
import { faInfo } from '@fortawesome/free-solid-svg-icons';
import { ToastService } from 'src/app/services/common/toast.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-suppliers-list',
  templateUrl: './suppliers-list.component.html',
  styleUrls: ['./suppliers-list.component.css'],
})
export class SuppliersListComponent implements OnInit {
  supplierList: any[] = [];
  filteredSuppliers: any[] = [];
  deleteFilter: boolean = false;
  supplierSort: string = 'Default';
  locationSort: string = 'Default';
  searchTerm: string = '';
  supplierToDeleteId: number | null = null;
  supplierToRestoreId: number | null = null;
  deleteMessage: string = '';
  restoreMessage: string = '';
  isLoading: boolean = true;

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  faInfo = faInfo;
  faImage = faImage;

  placeholder: string =
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1706758794/asj/suppliers/supplier-placeholder_zhufia.png';

  constructor(
    private router: Router,
    private supplierService: SupplierService,
    private toastService: ToastService
  ) {}

  ngOnInit() {
    this.getSuppliers();
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res;
      this.filterSuppliers();
      this.updateTotalPages();

      setTimeout(() => {
        this.isLoading = false;
      }, 500);
    });
  }

  addSupplier() {
    this.router.navigate(['/suppliers/add']);
  }

  openSupplier(id: number) {
    this.router.navigate([`/suppliers/${id}`]);
  }

  editSupplier(id: number) {
    this.router.navigate([`/suppliers/edit/${id}`]);
  }

  confirmDelete(id: number, code: string, businessName: string) {
    this.deleteMessage = `<div>¿Está seguro de que desea eliminar el proveedor?</div>
    <div class="mt-2">${businessName} <small>(Código: ${code})</small></div>`;
    this.supplierToDeleteId = id;
  }

  confirmRestore(id: number, code: string, businessName: string) {
    this.restoreMessage = `<div>¿Está seguro de que desea restaurar el proveedor?</div>
    <div class="mt-2">${businessName} <small>(Código: ${code})</small></div>`;
    this.supplierToRestoreId = id;
  }

  deleteSupplier() {
    if (this.supplierToDeleteId) {
      this.supplierService
        .deleteSupplier(this.supplierToDeleteId)
        .subscribe((res) => {
          this.getSuppliers();
          this.supplierToDeleteId = null;
          this.toastService.showSuccessToast(
            'Proveedor eliminado correctamente!'
          );
        });
    }
  }

  restoreSupplier() {
    if (this.supplierToRestoreId) {
      this.supplierService
        .restoreSupplier(this.supplierToRestoreId)
        .subscribe((res) => {
          this.toastService.showSuccessToast(
            'Proveedor restaurado correctamente!'
          );

          this.supplierService.getSuppliers().subscribe((res) => {
            this.supplierList = res;

            if (!this.hasDeletedSuppliers()) {
              this.deleteFilter = false;
            }

            this.filterSuppliers();
          });
        });
    }
  }

  filterSuppliers() {
    this.currentPage = 1;
    this.filteredSuppliers = [...this.supplierList];

    // Delete Filter
    if (this.deleteFilter) {
      this.filteredSuppliers = this.filteredSuppliers.filter(
        (item) => item.isDeleted
      );
    } else {
      this.filteredSuppliers = this.filteredSuppliers.filter(
        (item) => !item.isDeleted
      );
    }

    // Search Filter
    if (this.searchTerm) {
      this.filteredSuppliers = this.filteredSuppliers.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );
    }

    // Supplier Sort
    if (this.supplierSort === 'Ascending') {
      this.filteredSuppliers.sort((a, b) =>
        a.businessName.localeCompare(b.businessName)
      );
    } else if (this.supplierSort === 'Descending') {
      this.filteredSuppliers.sort((a, b) =>
        b.businessName.localeCompare(a.businessName)
      );
    }

    // Location Sort
    if (this.locationSort === 'Ascending') {
      this.filteredSuppliers.sort(
        (a: any, b: any) =>
          a.address.state.country.name
            .toLowerCase()
            .localeCompare(b.address.state.country.name.toLowerCase()) ||
          a.address.state.name
            .toLowerCase()
            .localeCompare(b.address.state.name.toLowerCase())
      );
    } else if (this.locationSort === 'Descending') {
      this.filteredSuppliers.sort(
        (a: any, b: any) =>
          b.address.state.country.name
            .toLowerCase()
            .localeCompare(a.address.state.country.name.toLowerCase()) ||
          a.address.state.name
            .toLowerCase()
            .localeCompare(b.address.state.name.toLowerCase())
      );
    }

    this.updateTotalPages();
  }

  updatePage(navigation: 'prev' | 'next' | 'first' | 'last'): void {
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

  updateTotalPages(): void {
    const minPages = 1;

    this.totalPages = Math.max(
      minPages,
      Math.ceil(this.filteredSuppliers.length / this.itemsPerPage)
    );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredSuppliers.length;
  }

  calculateRange(): string {
    const start = (this.currentPage - 1) * this.itemsPerPage + 1;
    const end = Math.min(
      this.currentPage * this.itemsPerPage,
      this.filteredSuppliers.length
    );
    return `Mostrando ${start} - ${end} de ${this.filteredSuppliers.length} proveedores`;
  }

  onCheckboxChange(event: any) {
    const checked = event.target.checked;

    if (checked) {
      this.deleteFilter = true;
    } else {
      this.deleteFilter = false;
    }

    this.filterSuppliers();
  }

  hasDeletedSuppliers() {
    return this.supplierList.some((supplier) => supplier.isDeleted);
  }

  updateSupplierSort() {
    switch (this.supplierSort) {
      case 'Default':
        this.supplierSort = 'Ascending';
        break;
      case 'Ascending':
        this.supplierSort = 'Descending';
        break;
      default:
        this.supplierSort = 'Default';
        break;
    }

    this.locationSort = 'Default';
    this.filterSuppliers();
  }

  updateLocationSort() {
    switch (this.locationSort) {
      case 'Default':
        this.locationSort = 'Ascending';
        break;
      case 'Ascending':
        this.locationSort = 'Descending';
        break;
      default:
        this.locationSort = 'Default';
        break;
    }

    this.supplierSort = 'Default';
    this.filterSuppliers();
  }

  handleImageError(event: any) {
    event.target.src = this.placeholder;
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faImage } from '@fortawesome/free-regular-svg-icons';
import { Category } from 'src/app/models/product/ICategory';
import { Product } from 'src/app/models/product/IProduct';
import { ToastService } from 'src/app/services/common/toast.service';
import { CategoryService } from 'src/app/services/product/category.service';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
})
export class ProductsListComponent implements OnInit {
  productList: Product[] = [];
  filteredProducts: Product[] = [];
  categoryList: Category[] = [];
  categoryFilter: string = 'Categoría';
  deleteFilter: boolean = false;
  nameSort: string = 'Default';
  priceSort: string = 'Default';
  searchTerm: string = '';
  productToDeleteId: number | null = null;
  productToRestoreId: number | null = null;
  deleteMessage: string = '';
  restoreMessage: string = '';
  isLoading: boolean = true;

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  faImage = faImage;

  placeholder: string =
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1706757607/asj/products/product-placeholder_mpyyog.png';

  constructor(
    private router: Router,
    private productService: ProductService,
    private toastService: ToastService,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.getProducts();
    this.getCategories();
  }

  getProducts() {
    this.productService.getProducts().subscribe((res) => {
      this.productList = res.filter((product) => !product.supplier.isDeleted);
      this.filterProducts();
      this.updateTotalPages();

      setTimeout(() => {
        this.isLoading = false;
      }, 500);
    });
  }

  getCategories() {
    this.categoryService.getCategories().subscribe((res) => {
      this.categoryList = res;
    });
  }

  addProduct() {
    this.router.navigate(['/products/add']);
  }

  openProduct(id: number) {
    this.router.navigate([`/products/${id}`]);
  }

  editProduct(id: number) {
    this.router.navigate([`/products/edit/${id}`]);
  }

  confirmDelete(id: number, sku: string, name: string) {
    this.deleteMessage = `<div>¿Está seguro de que desea eliminar el producto?</div>
    <div class="mt-2">${name} <small>(SKU: ${sku})</small></div>`;
    this.productToDeleteId = id;
  }

  confirmRestore(id: number, sku: string, name: string) {
    this.restoreMessage = `<div>¿Está seguro de que desea restaurar el producto?</div>
    <div class="mt-2">${name} <small>(SKU: ${sku})</small></div>`;
    this.productToRestoreId = id;
  }

  deleteProduct() {
    if (this.productToDeleteId) {
      this.productService
        .deleteProduct(this.productToDeleteId)
        .subscribe((res) => {
          this.getProducts();
          this.productToDeleteId = null;
          this.toastService.showSuccessToast(
            'Producto eliminado correctamente!'
          );
        });
    }
  }

  restoreProduct() {
    if (this.productToRestoreId) {
      this.productService
        .restoreProduct(this.productToRestoreId)
        .subscribe((res) => {
          this.toastService.showSuccessToast(
            'Producto restaurado correctamente!'
          );

          this.productService.getProducts().subscribe((res) => {
            this.productList = res.filter(
              (product) => !product.supplier.isDeleted
            );

            if (!this.hasDeletedProducts()) {
              this.deleteFilter = false;
            }

            this.filterProducts();
          });
        });
    }
  }

  filterProducts() {
    this.currentPage = 1;
    this.filteredProducts = [...this.productList];

    // Delete Filter
    if (this.deleteFilter) {
      this.filteredProducts = this.filteredProducts.filter(
        (item) => item.isDeleted
      );
    } else {
      this.filteredProducts = this.filteredProducts.filter(
        (item) => !item.isDeleted
      );
    }

    // Category Filter
    if (this.categoryFilter !== 'Categoría') {
      this.filteredProducts = this.filteredProducts.filter((item) => {
        if (item.category?.name) {
          return item.category.name
            .toLowerCase()
            .includes(this.categoryFilter.toLowerCase());
        }
        return false;
      });
    }

    // Search Filter
    if (this.searchTerm) {
      this.filteredProducts = this.filteredProducts.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );
    }

    // Name Sort
    if (this.nameSort === 'Ascending') {
      this.filteredProducts.sort((a, b) => a.name.localeCompare(b.name));
    } else if (this.nameSort === 'Descending') {
      this.filteredProducts.sort((a, b) => b.name.localeCompare(a.name));
    }

    // Price Sort
    if (this.priceSort === 'Ascending') {
      this.filteredProducts.sort((a, b) => a.price! - b.price!);
    } else if (this.priceSort === 'Descending') {
      this.filteredProducts.sort((a, b) => b.price! - a.price!);
    }

    this.updateTotalPages();
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
      Math.ceil(this.filteredProducts.length / this.itemsPerPage)
    );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredProducts.length;
  }

  calculateRange(): string {
    const start = (this.currentPage - 1) * this.itemsPerPage + 1;
    const end = Math.min(
      this.currentPage * this.itemsPerPage,
      this.filteredProducts.length
    );
    return `Mostrando ${start} - ${end} de ${this.filteredProducts.length} productos`;
  }

  updateCategoryFilter(category: string) {
    this.categoryFilter = category;
    this.filterProducts();
  }

  updateNameSort() {
    switch (this.nameSort) {
      case 'Default':
        this.nameSort = 'Ascending';
        break;
      case 'Ascending':
        this.nameSort = 'Descending';
        break;
      default:
        this.nameSort = 'Default';
        break;
    }

    this.priceSort = 'Default';
    this.filterProducts();
  }

  updatePriceSort() {
    switch (this.priceSort) {
      case 'Default':
        this.priceSort = 'Ascending';
        break;
      case 'Ascending':
        this.priceSort = 'Descending';
        break;
      default:
        this.priceSort = 'Default';
        break;
    }

    this.nameSort = 'Default';
    this.filterProducts();
  }

  onCheckboxChange(event: any) {
    const checked = event.target.checked;

    if (checked) {
      this.deleteFilter = true;
    } else {
      this.deleteFilter = false;
    }

    this.filterProducts();
  }

  hasDeletedProducts() {
    return this.productList.some((product) => product.isDeleted);
  }

  handleImageError(event: any) {
    event.target.src = this.placeholder;
  }
}

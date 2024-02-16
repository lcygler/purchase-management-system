import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product/IProduct';
import { ToastService } from 'src/app/services/common/toast.service';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-products-detail',
  templateUrl: './products-detail.component.html',
  styleUrls: ['./products-detail.component.css'],
})
export class ProductsDetailComponent implements OnInit {
  product: Product = {
    sku: '',
    name: '',
    description: '',
    price: null,
    category: {},
    supplier: {},
  };

  productId: number | null = null;
  restoreMessage: string = '';

  placeholder: string =
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1706757607/asj/products/product-placeholder_mpyyog.png';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location,
    private toastService: ToastService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('productId');

    if (id) {
      this.productId = parseInt(id);
      this.getProductById(this.productId);
    }
  }

  getProductById(id: number) {
    if (id) {
      this.productService.getProductById(id).subscribe({
        next: (res) => {
          this.product = res;
        },
        error: (error) => {
          if (error.status === 404) {
            this.router.navigate(['/not-found']);
          }
        },
      });
    }
  }

  editProduct() {
    this.router.navigate([`/products/edit/${this.productId}`]);
  }

  navigateBack() {
    this.location.back();
  }

  confirmRestore() {
    this.restoreMessage = `<div>¿Está seguro de que desea restaurar el producto?</div>
    <div class="mt-2">${this.product.name} <small>(SKU: ${this.product.sku})</small></div>`;
  }

  restoreProduct() {
    this.productService.restoreProduct(this.productId!).subscribe((res) => {
      this.toastService.showSuccessToast('Proveedor restaurado correctamente!');
      this.getProductById(this.productId!);
    });
  }

  handleImageError(event: any) {
    event.target.src = this.placeholder;
  }
}

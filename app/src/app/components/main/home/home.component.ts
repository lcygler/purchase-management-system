import { Component, OnInit } from '@angular/core';
import {
  faArrowRight,
  faDollarSign,
  faFileLines,
  faTags,
  faUsers,
} from '@fortawesome/free-solid-svg-icons';
import { Order } from 'src/app/models/order/IOrder';
import { Product } from 'src/app/models/product/IProduct';
import { Supplier } from 'src/app/models/supplier/ISupplier';
import { OrderDetailService } from 'src/app/services/order/order-detail.service';
import { OrderService } from 'src/app/services/order/order.service';
import { ProductService } from 'src/app/services/product/product.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';
import { TopProduct } from 'src/app/types/TTopProduct';
import { TopSupplier } from 'src/app/types/TTopSupplier';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  orderList: Order[] = [];
  supplierList: Supplier[] = [];
  productList: Product[] = [];
  topProducts: TopProduct[] = [];
  topSuppliers: TopSupplier[] = [];
  isLoading: boolean = true;
  medals: string[] = ['🥇', '🥈', '🥉'];

  faArrowRight = faArrowRight;
  faDollarSign = faDollarSign;
  faFileLines = faFileLines;
  faTags = faTags;
  faUsers = faUsers;

  constructor(
    private orderService: OrderService,
    private supplierService: SupplierService,
    private productService: ProductService,
    private orderDetailService: OrderDetailService
  ) {}

  ngOnInit() {
    this.getSuppliers();
    this.getProducts();
    this.getOrders();
    this.getTopSuppliers();
    this.getTopProducts();
    this.hideLoader();
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

  getOrders() {
    this.orderService.getOrders().subscribe((res) => {
      this.orderList = res;
      // this.orderList = res.filter((o) => o.status?.name !== 'Cancelado');
    });
  }

  getTopProducts() {
    this.orderDetailService.getTopProducts().subscribe((res) => {
      this.topProducts = res;
    });
  }

  getTopSuppliers() {
    this.orderService.getTopSuppliers().subscribe((res) => {
      this.topSuppliers = res;
    });
  }

  hideLoader() {
    setTimeout(() => {
      this.isLoading = false;
    }, 500);
  }
}

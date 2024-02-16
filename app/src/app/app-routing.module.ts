import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/main/home/home.component';
import { LoginFormComponent } from './components/main/login/login-form/login-form.component';
import { ManagementComponent } from './components/main/management/management.component';
import { NotFoundComponent } from './components/main/not-found/not-found.component';
import { OrdersFormComponent } from './components/main/orders/orders-form/orders-form.component';
import { OrdersListComponent } from './components/main/orders/orders-list/orders-list.component';
import { ProductsDetailComponent } from './components/main/products/products-detail/products-detail.component';
import { ProductsFormComponent } from './components/main/products/products-form/products-form.component';
import { ProductsListComponent } from './components/main/products/products-list/products-list.component';
import { SuppliersDetailComponent } from './components/main/suppliers/suppliers-detail/suppliers-detail.component';
import { SuppliersFormComponent } from './components/main/suppliers/suppliers-form/suppliers-form.component';
import { SuppliersListComponent } from './components/main/suppliers/suppliers-list/suppliers-list.component';
import { AuthGuard } from './guards/auth.guard';
import { NumberGuard } from './guards/number.guard';
import { LoginPassComponent } from './components/main/login/login-pass/login-pass.component';

const suppliersRoutes: Routes = [
  { path: '', component: SuppliersListComponent },
  { path: 'add', component: SuppliersFormComponent },
  { path: 'edit/:supplierId', component: SuppliersFormComponent, canActivate: [NumberGuard] },
  { path: ':supplierId', component: SuppliersDetailComponent, canActivate: [NumberGuard] },
];

const productsRoutes: Routes = [
  { path: '', component: ProductsListComponent },
  { path: 'add', component: ProductsFormComponent },
  { path: 'edit/:productId', component: ProductsFormComponent, canActivate: [NumberGuard] },
  { path: ':productId', component: ProductsDetailComponent, canActivate: [NumberGuard] },
];

const ordersRoutes: Routes = [
  { path: '', component: OrdersListComponent },
  { path: 'add', component: OrdersFormComponent },
  { path: 'edit/:orderId', component: OrdersFormComponent, canActivate: [NumberGuard] },
  { path: ':orderId', component: OrdersFormComponent, canActivate: [NumberGuard] },
];

const managementRoutes: Routes = [
  { path: 'categories', component: ManagementComponent },
  { path: 'industries', component: ManagementComponent },
  { path: 'categories/:id', component: ManagementComponent, canActivate: [NumberGuard] },
  { path: 'industries/:id', component: ManagementComponent, canActivate: [NumberGuard] },
  { path: '', pathMatch: 'full', redirectTo: 'categories' },
];

const loginRoutes: Routes = [
  { path: '', component: LoginFormComponent },
  { path: 'pass', component: LoginPassComponent, canActivate: [AuthGuard] },
];

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'suppliers', children: suppliersRoutes, canActivate: [AuthGuard] },
  { path: 'products', children: productsRoutes, canActivate: [AuthGuard] },
  { path: 'orders', children: ordersRoutes, canActivate: [AuthGuard] },
  { path: 'management', children: managementRoutes, canActivate: [AuthGuard] },
  { path: 'login', children: loginRoutes },
  { path: 'not-found', component: NotFoundComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: '**', pathMatch: 'full', redirectTo: 'not-found' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

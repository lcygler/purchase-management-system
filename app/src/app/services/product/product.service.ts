import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Product } from '../../models/product/IProduct';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private API_URL = 'http://localhost:8080/products';

  constructor(private http: HttpClient) {}

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.API_URL}`).pipe(
      map((products) => products.sort((a, b) => a.name.localeCompare(b.name))),
      catchError((error) => {
        console.error('Error fetching product list:', error);
        return throwError(() => error);
      })
    );
  }

  public getProductsByRange(start: number, end: number): Observable<Product[]> {
    return this.http
      .get<Product[]>(`${this.API_URL}?_start=${start}&_end=${end}`)
      .pipe(
        map((products) =>
          products
            .filter((product) => !product.isDeleted)
            .sort((a, b) => a.name.localeCompare(b.name))
        ),
        catchError((error) => {
          console.error('Error fetching products by range:', error);
          return throwError(() => error);
        })
      );
  }

  public getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching product by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getProductsBySupplier(id: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.API_URL}?supplierId=${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching products by supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public getProductSku(): Observable<string> {
    return this.http.get(`${this.API_URL}/sku`, { responseType: 'text' }).pipe(
      catchError((error) => {
        console.error('Error fetching product sku:', error);
        return throwError(() => error);
      })
    );
  }

  public createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.API_URL}`, product).pipe(
      catchError((error) => {
        console.error('Error creating product:', error);
        return throwError(() => error);
      })
    );
  }

  public updateProduct(
    id: number,
    product: Partial<Product>
  ): Observable<Product> {
    return this.http.patch<Product>(`${this.API_URL}/${id}`, product).pipe(
      catchError((error) => {
        console.error('Error updating product:', error);
        return throwError(() => error);
      })
    );
  }

  public restoreProduct(id: number): Observable<Product> {
    return this.http
      .patch<Product>(`${this.API_URL}/${id}`, { isDeleted: false })
      .pipe(
        catchError((error) => {
          console.error('Error restoring product:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteProduct(id: number): Observable<Product> {
    return this.http
      .patch<Product>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting product:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting product:', error);
        return throwError(() => error);
      })
    );
  }
}

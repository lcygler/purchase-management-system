import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { TopProduct } from 'src/app/types/TTopProduct';
import { OrderDetail } from '../../models/order/IOrderDetail';

@Injectable({
  providedIn: 'root',
})
export class OrderDetailService {
  private API_URL = 'http://localhost:8080/order-details';

  constructor(private http: HttpClient) {}

  public getOrderDetails(): Observable<OrderDetail[]> {
    return this.http.get<OrderDetail[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching order detail list:', error);
        return throwError(() => error);
      })
    );
  }

  public getOrderDetailById(id: number): Observable<OrderDetail> {
    return this.http.get<OrderDetail>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching order detail by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getTopProducts(): Observable<TopProduct[]> {
    return this.http.get<TopProduct[]>(`${this.API_URL}/top-products`).pipe(
      catchError((error) => {
        console.error('Error fetching top products:', error);
        return throwError(() => error);
      })
    );
  }

  public createOrderDetail(orderDetail: OrderDetail): Observable<OrderDetail> {
    return this.http.post<OrderDetail>(`${this.API_URL}`, orderDetail).pipe(
      catchError((error) => {
        console.error('Error creating order detail:', error);
        return throwError(() => error);
      })
    );
  }

  public createOrderDetails(
    orderDetails: OrderDetail[]
  ): Observable<OrderDetail[]> {
    return this.http
      .post<OrderDetail[]>(`${this.API_URL}/bulk-create`, orderDetails)
      .pipe(
        catchError((error) => {
          console.error('Error creating order details:', error);
          return throwError(() => error);
        })
      );
  }

  public updateOrderDetail(
    id: number,
    orderDetail: Partial<OrderDetail>
  ): Observable<OrderDetail> {
    return this.http
      .patch<OrderDetail>(`${this.API_URL}/${id}`, orderDetail)
      .pipe(
        catchError((error) => {
          console.error('Error updating order detail:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteOrderDetail(id: number): Observable<OrderDetail> {
    return this.http
      .patch<OrderDetail>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting order detail:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteOrderDetail(id: number): Observable<OrderDetail> {
    return this.http.delete<OrderDetail>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting order detail:', error);
        return throwError(() => error);
      })
    );
  }
}

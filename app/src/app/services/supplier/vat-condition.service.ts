import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { VatCondition } from '../../models/supplier/IVatCondition';

@Injectable({
  providedIn: 'root',
})
export class VatConditionService {
  private API_URL = 'http://localhost:8080/vat-conditions';

  constructor(private http: HttpClient) {}

  public getVatConditions(): Observable<VatCondition[]> {
    return this.http.get<VatCondition[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching vat condition list:', error);
        return throwError(() => error);
      })
    );
  }

  public getVatConditionById(id: number): Observable<VatCondition> {
    return this.http.get<VatCondition>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching vat condition by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createVatCondition(
    vatCondition: VatCondition
  ): Observable<VatCondition> {
    return this.http.post<VatCondition>(`${this.API_URL}`, vatCondition).pipe(
      catchError((error) => {
        console.error('Error creating vat condition:', error);
        return throwError(() => error);
      })
    );
  }

  public updateVatCondition(
    id: number,
    vatCondition: Partial<VatCondition>
  ): Observable<VatCondition> {
    return this.http
      .patch<VatCondition>(`${this.API_URL}/${id}`, vatCondition)
      .pipe(
        catchError((error) => {
          console.error('Error updating vat condition:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteVatCondition(id: number): Observable<VatCondition> {
    return this.http
      .patch<VatCondition>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting vat condition:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteVatCondition(id: number): Observable<VatCondition> {
    return this.http.delete<VatCondition>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting vat condition:', error);
        return throwError(() => error);
      })
    );
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { TaxInformation } from '../../models/supplier/ITaxInformation';

@Injectable({
  providedIn: 'root',
})
export class TaxInformationService {
  private API_URL = 'http://localhost:8080/tax-information';

  constructor(private http: HttpClient) {}

  public getTaxInformation(): Observable<TaxInformation[]> {
    return this.http.get<TaxInformation[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching tax information list:', error);
        return throwError(() => error);
      })
    );
  }

  public getTaxInformationById(id: number): Observable<TaxInformation> {
    return this.http.get<TaxInformation>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching tax information by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createTaxInformation(
    taxInformation: TaxInformation
  ): Observable<TaxInformation> {
    return this.http
      .post<TaxInformation>(`${this.API_URL}`, taxInformation)
      .pipe(
        catchError((error) => {
          console.error('Error creating tax information:', error);
          return throwError(() => error);
        })
      );
  }

  public updateTaxInformation(
    id: number,
    taxInformation: Partial<TaxInformation>
  ): Observable<TaxInformation> {
    return this.http
      .patch<TaxInformation>(`${this.API_URL}/${id}`, taxInformation)
      .pipe(
        catchError((error) => {
          console.error('Error updating tax information:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteTaxInformation(id: number): Observable<TaxInformation> {
    return this.http
      .patch<TaxInformation>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting tax information:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteTaxInformation(id: number): Observable<TaxInformation> {
    return this.http.delete<TaxInformation>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting tax information:', error);
        return throwError(() => error);
      })
    );
  }
}

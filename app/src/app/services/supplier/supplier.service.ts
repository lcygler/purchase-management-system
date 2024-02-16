import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Supplier } from '../../models/supplier/ISupplier';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {
  private API_URL = 'http://localhost:8080/suppliers';

  constructor(private http: HttpClient) {}

  public getSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.API_URL}`).pipe(
      map((suppliers) =>
        suppliers.sort((a, b) => a.businessName.localeCompare(b.businessName))
      ),
      catchError((error) => {
        console.error('Error fetching supplier list:', error);
        return throwError(() => error);
      })
    );
  }

  public getSuppliersByRange(
    start: number,
    end: number
  ): Observable<Supplier[]> {
    return this.http
      .get<Supplier[]>(`${this.API_URL}?_start=${start}&_end=${end}`)
      .pipe(
        map((suppliers) => suppliers.filter((supplier) => !supplier.isDeleted)),
        catchError((error) => {
          console.error('Error fetching suppliers by range:', error);
          return throwError(() => error);
        })
      );
  }

  public getSupplierById(id: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching supplier by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getSupplierCode(): Observable<string> {
    return this.http.get(`${this.API_URL}/code`, { responseType: 'text' }).pipe(
      catchError((error) => {
        console.error('Error fetching supplier code:', error);
        return throwError(() => error);
      })
    );
  }

  public createSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.post<Supplier>(`${this.API_URL}`, supplier).pipe(
      catchError((error) => {
        console.error('Error creating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public updateSupplier(id: number, supplier: Supplier): Observable<Supplier> {
    return this.http.patch<Supplier>(`${this.API_URL}/${id}`, supplier).pipe(
      catchError((error) => {
        console.error('Error updating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public restoreSupplier(id: number): Observable<Supplier> {
    return this.http
      .patch<Supplier>(`${this.API_URL}/${id}`, { isDeleted: false })
      .pipe(
        catchError((error) => {
          console.error('Error restoring supplier:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteSupplier(id: number): Observable<Supplier> {
    return this.http
      .patch<Supplier>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error updating supplier:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteSupplier(id: number): Observable<Supplier> {
    return this.http.delete<Supplier>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting supplier:', error);
        return throwError(() => error);
      })
    );
  }
}

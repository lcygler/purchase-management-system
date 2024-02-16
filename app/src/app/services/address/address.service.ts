import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Address } from '../../models/address/IAddress';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  private API_URL = 'http://localhost:8080/addresses';

  constructor(private http: HttpClient) {}

  public getAddresses(): Observable<Address[]> {
    return this.http.get<Address[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching address list:', error);
        return throwError(() => error);
      })
    );
  }

  public getAddressById(id: number): Observable<Address> {
    return this.http.get<Address>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching address by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(`${this.API_URL}`, address).pipe(
      catchError((error) => {
        console.error('Error creating address:', error);
        return throwError(() => error);
      })
    );
  }

  public updateAddress(
    id: number,
    address: Partial<Address>
  ): Observable<Address> {
    return this.http.patch<Address>(`${this.API_URL}/${id}`, address).pipe(
      catchError((error) => {
        console.error('Error updating address:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteAddress(id: number): Observable<Address> {
    return this.http
      .patch<Address>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting address:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteAddress(id: number): Observable<Address> {
    return this.http.delete<Address>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting address:', error);
        return throwError(() => error);
      })
    );
  }
}

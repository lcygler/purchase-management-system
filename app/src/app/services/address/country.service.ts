import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Country } from '../../models/address/ICountry';

@Injectable({
  providedIn: 'root',
})
export class CountryService {
  private API_URL = 'http://localhost:8080/countries';

  constructor(private http: HttpClient) {}

  public getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching countries list:', error);
        return throwError(() => error);
      })
    );
  }

  public getCountryById(id: number): Observable<Country> {
    return this.http.get<Country>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching country by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createCountry(country: Country): Observable<Country> {
    return this.http.post<Country>(`${this.API_URL}`, country).pipe(
      catchError((error) => {
        console.error('Error creating country:', error);
        return throwError(() => error);
      })
    );
  }

  public updateCountry(
    id: number,
    country: Partial<Country>
  ): Observable<Country> {
    return this.http.patch<Country>(`${this.API_URL}/${id}`, country).pipe(
      catchError((error) => {
        console.error('Error updating country:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteCountry(id: number): Observable<Country> {
    return this.http
      .patch<Country>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting country:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteCountry(id: number): Observable<Country> {
    return this.http.delete<Country>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting country:', error);
        return throwError(() => error);
      })
    );
  }
}

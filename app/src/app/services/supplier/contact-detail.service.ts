import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ContactDetail } from '../../models/supplier/IContactDetail';

@Injectable({
  providedIn: 'root',
})
export class ContactDetailService {
  private API_URL = 'http://localhost:8080/contact-details';

  constructor(private http: HttpClient) {}

  public getContactDetails(): Observable<ContactDetail[]> {
    return this.http.get<ContactDetail[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching contact detail list:', error);
        return throwError(() => error);
      })
    );
  }

  public getContactDetailById(id: number): Observable<ContactDetail> {
    return this.http.get<ContactDetail>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching contact detail by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createContactDetail(
    contactDetail: ContactDetail
  ): Observable<ContactDetail> {
    return this.http.post<ContactDetail>(`${this.API_URL}`, contactDetail).pipe(
      catchError((error) => {
        console.error('Error creating contact detail:', error);
        return throwError(() => error);
      })
    );
  }

  public updateContactDetail(
    id: number,
    contactDetail: Partial<ContactDetail>
  ): Observable<ContactDetail> {
    return this.http
      .patch<ContactDetail>(`${this.API_URL}/${id}`, contactDetail)
      .pipe(
        catchError((error) => {
          console.error('Error updating contact detail:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteContactDetail(id: number): Observable<ContactDetail> {
    return this.http
      .patch<ContactDetail>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting contact detail:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteContactDetail(id: number): Observable<ContactDetail> {
    return this.http.delete<ContactDetail>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting contact detail:', error);
        return throwError(() => error);
      })
    );
  }
}

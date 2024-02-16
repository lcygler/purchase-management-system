import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Status } from '../../models/order/IStatus';

@Injectable({
  providedIn: 'root',
})
export class StatusService {
  private API_URL = 'http://localhost:8080/statuses';

  constructor(private http: HttpClient) {}

  public getStatuses(): Observable<Status[]> {
    return this.http.get<Status[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching status list:', error);
        return throwError(() => error);
      })
    );
  }

  public getStatusById(id: number): Observable<Status> {
    return this.http.get<Status>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching status by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createStatus(status: Status): Observable<Status> {
    return this.http.post<Status>(`${this.API_URL}`, status).pipe(
      catchError((error) => {
        console.error('Error creating status:', error);
        return throwError(() => error);
      })
    );
  }

  public updateStatus(id: number, status: Partial<Status>): Observable<Status> {
    return this.http.patch<Status>(`${this.API_URL}/${id}`, status).pipe(
      catchError((error) => {
        console.error('Error updating status:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteStatus(id: number): Observable<Status> {
    return this.http
      .patch<Status>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting status:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteStatus(id: number): Observable<Status> {
    return this.http.delete<Status>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting status:', error);
        return throwError(() => error);
      })
    );
  }
}

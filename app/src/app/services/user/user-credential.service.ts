import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserCredential } from '../../models/user/IUserCredential';

@Injectable({
  providedIn: 'root',
})
export class UserCredentialService {
  private API_URL = 'http://localhost:8080/user-credentials';

  constructor(private http: HttpClient) {}

  public getUserCredentials(): Observable<UserCredential[]> {
    return this.http.get<UserCredential[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching user credential list:', error);
        return throwError(() => error);
      })
    );
  }

  public getUserCredentialById(id: number): Observable<UserCredential> {
    return this.http.get<UserCredential>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching user credential by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getUserCredentialByUserId(id: number): Observable<UserCredential> {
    return this.http.get<UserCredential>(`${this.API_URL}/user/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching user credential by user ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createUserCredential(
    userCredential: UserCredential
  ): Observable<UserCredential> {
    return this.http
      .post<UserCredential>(`${this.API_URL}`, userCredential)
      .pipe(
        catchError((error) => {
          console.error('Error creating user credential:', error);
          return throwError(() => error);
        })
      );
  }

  public updateUserCredential(
    id: number,
    userCredential: Partial<UserCredential>
  ): Observable<UserCredential> {
    return this.http
      .patch<UserCredential>(`${this.API_URL}/${id}`, userCredential)
      .pipe(
        catchError((error) => {
          console.error('Error updating user credential:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteUserCredential(id: number): Observable<UserCredential> {
    return this.http
      .patch<UserCredential>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting user credential:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteUserCredential(id: number): Observable<UserCredential> {
    return this.http.delete<UserCredential>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting user credential:', error);
        return throwError(() => error);
      })
    );
  }
}

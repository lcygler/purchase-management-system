import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Role } from '../../models/user/IRole';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  private API_URL = 'http://localhost:8080/roles';

  constructor(private http: HttpClient) {}

  public getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching role list:', error);
        return throwError(() => error);
      })
    );
  }

  public getRoleById(id: number): Observable<Role> {
    return this.http.get<Role>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching role by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createRole(role: Role): Observable<Role> {
    return this.http.post<Role>(`${this.API_URL}`, role).pipe(
      catchError((error) => {
        console.error('Error creating role:', error);
        return throwError(() => error);
      })
    );
  }

  public updateRole(id: number, role: Partial<Role>): Observable<Role> {
    return this.http.patch<Role>(`${this.API_URL}/${id}`, role).pipe(
      catchError((error) => {
        console.error('Error updating role:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteRole(id: number): Observable<Role> {
    return this.http
      .patch<Role>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting role:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteRole(id: number): Observable<Role> {
    return this.http.delete<Role>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting role:', error);
        return throwError(() => error);
      })
    );
  }
}

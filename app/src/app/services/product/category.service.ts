import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Category } from '../../models/product/ICategory';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private API_URL = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) {}

  public getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching category list:', error);
        return throwError(() => error);
      })
    );
  }

  public getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching category by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.API_URL}`, category).pipe(
      catchError((error) => {
        console.error('Error creating category:', error);
        return throwError(() => error);
      })
    );
  }

  public updateCategory(
    id: number,
    category: Partial<Category>
  ): Observable<Category> {
    return this.http.patch<Category>(`${this.API_URL}/${id}`, category).pipe(
      catchError((error) => {
        console.error('Error updating category:', error);
        return throwError(() => error);
      })
    );
  }

  public restoreCategory(id: number): Observable<Category> {
    return this.http
      .patch<Category>(`${this.API_URL}/${id}`, { isDeleted: false })
      .pipe(
        catchError((error) => {
          console.error('Error restoring category:', error);
          return throwError(() => error);
        })
      );
  }

  public deleteCategory(id: number): Observable<Category> {
    return this.http
      .patch<Category>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting category:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteCategory(id: number): Observable<Category> {
    return this.http.delete<Category>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting category:', error);
        return throwError(() => error);
      })
    );
  }
}

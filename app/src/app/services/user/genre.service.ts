import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Genre } from '../../models/user/IGenre';

@Injectable({
  providedIn: 'root',
})
export class GenreService {
  private API_URL = 'http://localhost:8080/genres';

  constructor(private http: HttpClient) {}

  public getGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching genre list:', error);
        return throwError(() => error);
      })
    );
  }

  public getGenreById(id: number): Observable<Genre> {
    return this.http.get<Genre>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching genre by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createGenre(genre: Genre): Observable<Genre> {
    return this.http.post<Genre>(`${this.API_URL}`, genre).pipe(
      catchError((error) => {
        console.error('Error creating genre:', error);
        return throwError(() => error);
      })
    );
  }

  public updateGenre(id: number, genre: Partial<Genre>): Observable<Genre> {
    return this.http.patch<Genre>(`${this.API_URL}/${id}`, genre).pipe(
      catchError((error) => {
        console.error('Error updating genre:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteGenre(id: number): Observable<Genre> {
    return this.http
      .patch<Genre>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting genre:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteGenre(id: number): Observable<Genre> {
    return this.http.delete<Genre>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting genre:', error);
        return throwError(() => error);
      })
    );
  }
}

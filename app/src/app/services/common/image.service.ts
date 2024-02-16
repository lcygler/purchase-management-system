import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Image } from '../../models/common/IImage';

@Injectable({
  providedIn: 'root',
})
export class ImageService {
  private API_URL = 'http://localhost:8080/images';

  constructor(private http: HttpClient) {}

  public getImages(): Observable<Image[]> {
    return this.http.get<Image[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching images list:', error);
        return throwError(() => error);
      })
    );
  }

  public getImageById(id: number): Observable<Image> {
    return this.http.get<Image>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching image by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createImage(image: Image): Observable<Image> {
    return this.http.post<Image>(`${this.API_URL}`, image).pipe(
      catchError((error) => {
        console.error('Error creating image:', error);
        return throwError(() => error);
      })
    );
  }

  public updateImage(id: number, image: Partial<Image>): Observable<Image> {
    return this.http.patch<Image>(`${this.API_URL}/${id}`, image).pipe(
      catchError((error) => {
        console.error('Error updating image:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteImage(id: number): Observable<Image> {
    return this.http
      .patch<Image>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting image:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteImage(id: number): Observable<Image> {
    return this.http.delete<Image>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting image:', error);
        return throwError(() => error);
      })
    );
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { State } from '../../models/address/IState';

@Injectable({
  providedIn: 'root',
})
export class StateService {
  private API_URL = 'http://localhost:8080/states';

  constructor(private http: HttpClient) {}

  public getStates(): Observable<State[]> {
    return this.http.get<State[]>(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching states list:', error);
        return throwError(() => error);
      })
    );
  }

  public getStateById(id: number): Observable<State> {
    return this.http.get<State>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching state by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getStatesByCountry(id: number): Observable<State[]> {
    return this.http.get<State[]>(`${this.API_URL}?countryId=${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching states by country:', error);
        return throwError(() => error);
      })
    );
  }

  public createState(state: State): Observable<State> {
    return this.http.post<State>(`${this.API_URL}`, state).pipe(
      catchError((error) => {
        console.error('Error creating state:', error);
        return throwError(() => error);
      })
    );
  }

  public updateState(id: number, state: Partial<State>): Observable<State> {
    return this.http.patch<State>(`${this.API_URL}/${id}`, state).pipe(
      catchError((error) => {
        console.error('Error updating state:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteState(id: number): Observable<State> {
    return this.http
      .patch<State>(`${this.API_URL}/${id}`, { isDeleted: true })
      .pipe(
        catchError((error) => {
          console.error('Error deleting state:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteState(id: number): Observable<State> {
    return this.http.delete<State>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting state:', error);
        return throwError(() => error);
      })
    );
  }
}

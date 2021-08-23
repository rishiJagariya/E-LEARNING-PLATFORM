import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class ElpServiceService {

  private userRestUrl: String = "http://localhost:8080/backend/user"
  private courseRestUrl: String = "http://localhost:8080/backend/course"
  private cartRestUrl: String = "http://localhost:8080/backend/cart"

  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  createUser(user: any): Observable<User> {
    return this.http
      .post<User>(
        this.userRestUrl + '/createuser',
        JSON.stringify(user),
        this.httpOptions
      )
      .pipe(retry(1), catchError(this.handleError))
  }

  handleError(err : any) {
    let errorMessage = ""
    if (err.error instanceof ErrorEvent)
      errorMessage = err.error.message
    else
      errorMessage = `Following error thrown from ELP Service ${err.code} : ${err.message}`

    alert(errorMessage)
    return throwError(errorMessage)
  }
}

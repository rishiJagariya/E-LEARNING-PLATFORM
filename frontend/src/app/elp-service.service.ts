import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from './user';
import { Course } from './course';

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
    //pares user to User type
    var newUser : User = {
      //userId: 0,
      userType: user.userType,
      username: user.username,
      password: user.password,
      fname: user.fname,
      lname: user.lname,
      dob: user.dob,
      phoneNo: user.phoneNo
    }

    return this.http
      .post<User>(
        this.userRestUrl + '/createuser',
        JSON.stringify(newUser)
      )
      .pipe(catchError(this.handleError))
  }

  createCourse(course : Course) : Observable<Course> {
    return this.http
      .post<Course>(
        this.courseRestUrl + '/createcourse',
        JSON.stringify(course)
      )
      .pipe(catchError(this.handleError))
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

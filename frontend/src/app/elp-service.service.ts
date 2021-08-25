import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from './user';
import { Course } from './course';
import { UserLoginInfo } from './userLoginInfo';
import { ResponseObject } from './responseObject';
import { UsernameAndCourse } from './usernameAndCourse';

@Injectable({
  providedIn: 'root'
})
export class ElpServiceService {

  private userRestUrl: String = "http://localhost:8080/backend/user"
  private courseRestUrl: String = "http://localhost:8080/backend/course"
  private cartRestUrl: String = "http://localhost:8080/backend/cart"

  constructor(private http: HttpClient) {}

  //object for add
  public trainerUpdateCourseData : UsernameAndCourse = {
    course:  {
      courseId : 0,
      courseName : '',
      description : '',
      category : '',
      fee: 0,
      duration: 0,
      rating : 0,
      trainerId : 0
    },
    username: ''
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Accept' : 'application/json',
      'Content-Type': 'application/json',

    }),
  };

  /* USER FUNCTIONS */

  createUser(user: any): Observable<ResponseObject> {
    //pares user to User type
    var newUser : User = {
      userType: user.userType,
      username: user.username,
      password: user.password,
      fname: user.fname,
      lname: user.lname,
      dob: user.dob,
      phoneNo: user.phoneNo
    }

    return this.http
      .post<ResponseObject>(
        this.userRestUrl + '/createuser',
        JSON.stringify(newUser),
        this.httpOptions,
      )
      .pipe(catchError(this.handleError))
  }

  userLogin(userLoginInfo : UserLoginInfo) : Observable<ResponseObject>  {
    return this.http
      .post<ResponseObject>(
        this.userRestUrl + '/userlogin',
        JSON.stringify(userLoginInfo),
        this.httpOptions
        //incomplete
      )
  }

  forgotPassword(forgotPasswordData : UserLoginInfo) : Observable<ResponseObject> {
    return this.http
      .post<ResponseObject>(
        this.userRestUrl + '/forgotpassword',
        JSON.stringify(forgotPasswordData),
        this.httpOptions
      )
  }

  
  /* COURSE RELATED FUNCTIONS */

  createCourse(course : Course) : Observable<ResponseObject> {
    return this.http
      .post<ResponseObject>(
        this.courseRestUrl + '/createcourse',
        JSON.stringify(course),
        this.httpOptions
      )
      .pipe(catchError(this.handleError))
  }

  updateCourse(updateCourseData : UsernameAndCourse) : Observable<ResponseObject> {
    return this.http
      .put<ResponseObject>(
        this.courseRestUrl + '/updatecourse',
        JSON.stringify(updateCourseData),
        this.httpOptions
      )
      .pipe(catchError(this.handleError))
  }

  loadTrainerCourses(username : string) : Observable<Course[]> {
    return this.http
      .get<Course[]>(this.courseRestUrl + '/getTrainerCourseList/' + username, this.httpOptions)
      .pipe(catchError(this.handleError))
  }

  loadEnrolledCourses(userId : Number) : Observable<Course[]> {
    return this.http
      .get<Course[]>(this.courseRestUrl + '/getEnrolledCourseList' + '/' + userId, this.httpOptions)
      .pipe(catchError(this.handleError))
  }

  loadCourses() : Observable<Course[]> {
    return this.http
      .get<Course[]>(
        this.courseRestUrl + '/getCourseList',
      )
  }


  /* Cart related functions */
  getCartItems(userId : Number) : Observable<Course[]> {
    return this.http
      .get<Course[]>(this.cartRestUrl +'/viewCart/' + userId ,this.httpOptions)
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

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from './user';
import { Course } from './course';
import { UserLoginInfo } from './userLoginInfo';
import { ResponseObject } from './responseObject';
import { UsernameAndCourse } from './usernameAndCourse';
import { TrainerData } from './trainerData';
import { StudentData } from './studentData';

@Injectable({
  providedIn: 'root'
})
export class ElpServiceService {
 
  private userRestUrl: String = "http://localhost:8080/backend/user"
  private courseRestUrl: String = "http://localhost:8080/backend/course"
  private cartRestUrl: String = "http://localhost:8080/backend/cart"

  constructor(private http: HttpClient) {}

  private currentUserInfo : UserLoginInfo = {
    userType: '',
    username: '',
    password: ''
  }

  getUserInfo() {
    return this.currentUserInfo
  }

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

  getTrainer(username : string) : Observable<TrainerData>{
    return this.http
      .get<TrainerData>(
        this.userRestUrl + '/getTrainer/' + username,
        this.httpOptions
      )
  }

  getStudent(username : string) : Observable<StudentData> {
    return this.http
      .get<StudentData>(
        this.userRestUrl + '/getStudent/' + username,
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
      .get<Course[]>(this.courseRestUrl + '/getEnrolledCourseList/' + userId, this.httpOptions)
      .pipe(catchError(this.handleError))
  }

  loadCourses() : Observable<Course[]> {
    return this.http
      .get<Course[]>(
        this.courseRestUrl + '/getCourseList',
      )
  }

  deleteCourse(username : string, courseId : Number) : Observable<ResponseObject> {
    return this.http
      .delete<ResponseObject>(
        this.courseRestUrl + '/deletecourse/' + username + '/' + courseId,
        this.httpOptions
      )
  }

  /*  CART RELATED FUNCTIONS  */

  unenrollFromCourse(courseId: Number, userId: Number) : Observable<ResponseObject> {
    return this.http
      .delete<ResponseObject>(
        this.cartRestUrl + '/unenrollFromCourse/' + userId + '/' + courseId,
        this.httpOptions
      )
  }

  getCartItems(userId: Number): Observable<Course[]> {
    return this.http
      .get<Course[]>(
        this.cartRestUrl + '/viewCart/' + userId,
        this.httpOptions
      )
      .pipe(catchError(this.handleError))
  }

  addToCart(courseId : Number, userId : Number) : Observable<ResponseObject>{
    //Add this courseId to this student's cart 
    return this.http
      .get<ResponseObject>(
        this.cartRestUrl + '/addToCart/' + courseId + '/' + userId,
        this.httpOptions
      ).pipe(catchError(this.handleError))
  }

  deleteCart(courseId : Number, studentId : Number) : Observable<ResponseObject>{
    return this.http  
      .delete<ResponseObject>(
        this.cartRestUrl + '/removeFromCart/' + courseId + '/' + studentId,
        this.httpOptions
      ).pipe(catchError(this.handleError))
  }

  checkout(userId : Number) : Observable<ResponseObject>{
    return this.http
      .get<ResponseObject>(
        this.cartRestUrl + '/checkout/' + userId,
        this.httpOptions
      ).pipe(catchError(this.handleError))
  }

  /* ERROR HANDLING FUNCIONS */

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

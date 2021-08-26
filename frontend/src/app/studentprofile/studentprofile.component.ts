import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';
import { StudentData } from '../studentData';

@Component({
  selector: 'studentprofile',
  templateUrl: './studentprofile.component.html',
  styleUrls: ['./studentprofile.component.css']
})
export class StudentprofileComponent implements OnInit {
  constructor( public restApi: ElpServiceService, public router: Router, private authService : AuthService ) { }

  searchText : string = ''
  studentData : StudentData = {
    userId: 0,
    userType: '',
    username: '',
    password: '',
    fname: '',
    lname: '',
    dob: '',
    phoneNo: '',
    enroll: []
  }
  courseList : Course[] = []
    
  ngOnInit(): void {
    this.studentData = this.authService.getStudent()
    this.loadCourses()
  }

  //for search course
  submitForm(fromData : any) {

  }

  loadCourses() {
    return this.restApi
      .loadEnrolledCourses(this.studentData.userId)
      .subscribe((data) => { console.log(data); this.courseList = data})
  }

  courseCompletion() {
    console.log("Course is completed")
  }

  unenrollCourse(courseId : Number) {
    console.log("im here in unenroll course")
    var userId : Number = this.studentData.userId
    return this.restApi.unenrollFromCourse(courseId, userId)
    .subscribe(data => { console.log(data) })
  }

  logout() {
    console.log("logout")
    this.authService.logout()
    this.router.navigate(['/login'])
  }

}

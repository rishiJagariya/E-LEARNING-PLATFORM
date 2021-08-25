import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';
import { StudentData } from '../studentData';

@Component({
  selector: 'studentprofile',
  templateUrl: './studentprofile.component.html',
  styleUrls: ['./studentprofile.component.css']
})
export class StudentprofileComponent implements OnInit {
  constructor( public restApi: ElpServiceService, public router: Router ) { }

  searchText : string = ''
  studentData : StudentData = {
    userId: 0,
    userType: 'student',
    username: '',
    password: '',
    fname: 'Ritika',
    lname: 'X',
    dob: '',
    phoneNo: '9887736549',
    enroll: []
  }
  courseList : Course[] = [ {
    "courseId" : 1,
    "courseName" : "JAVA",
    "fee" : 400,
    "category" : "CS",
    "trainerId" : 101,
    "duration" : 0,
    "description" : "",
    "rating" : 0
    },
  ]
    
  ngOnInit(): void {
    this.loadCourses()
  }

  //form search course
  submitForm(fromData : any) {

  }

  loadCourses() {
    return this.restApi
      .loadEnrolledCourses(this.studentData.userId)
      .subscribe((data) => { console.log(data); this.courseList = data})
  }

  logout() {

  }

}

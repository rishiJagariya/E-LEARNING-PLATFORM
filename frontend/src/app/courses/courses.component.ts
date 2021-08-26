import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';
import { StudentData } from '../studentData';
import { UserFetched } from '../userFetched';

@Component({
  selector: 'courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  constructor(public restApi: ElpServiceService, public router: Router, private authService : AuthService) { }
  
  searchText : string = ''
  userid : Number = 0 
  courseList : Course[] = []

  ngOnInit(): void {
    this.loadCourses()
    //var studentInfo : StudentData = this.authService.getStudent()
    var userInfo : UserFetched =  this.authService.getStudent()
    this.userid = userInfo.userId
  }

  //form search course
  submitForm(fromData : any) {

  }

  loadCourses() {
    return this.restApi
      .loadCourses()
      .subscribe((data) => { this.courseList = data})
  }

  addToCart(course : Course) {
    console.log(course.courseId)
    return this.restApi.addToCart(course.courseId, this.userid)
      .subscribe(data => {
        console.log(data)
      })
  }
}


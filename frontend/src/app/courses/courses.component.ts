import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';

@Component({
  selector: 'courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  constructor(public restApi: ElpServiceService, public router: Router) { }
  
  searchText : string = ''

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
    {
      "courseId" : 1,
      "courseName" : "JAVA",
      "fee" : 400,
      "category" : "CS",
      "trainerId" : 101,
      "duration" : 0,
      "description" : "",
      "rating" : 0
      },{
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
      .loadCourses()
      .subscribe((data) => { this.courseList = data})
  }
}


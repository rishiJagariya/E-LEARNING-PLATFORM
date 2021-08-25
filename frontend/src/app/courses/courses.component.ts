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

  userid : Number = 27 //TODO

  courseList : Course[] = []

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

  addToCart(course : Course) {
    console.log(course.courseId)
    return this.restApi.addToCart(course.courseId, this.userid)
      .subscribe(data => {
        console.log(data)
      })
  }
}


import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';
import { TrainerData } from '../trainerData';

@Component({
  selector: 'trainerprofile',
  templateUrl: './trainerprofile.component.html',
  styleUrls: ['./trainerprofile.component.css']
})
export class TrainerprofileComponent implements OnInit {
  constructor( public restApi: ElpServiceService, public router: Router ) { }

  searchText : string = ''
  trainerData : TrainerData = {
    userId: 0,
    userType: 'trainer',
    username: '',
    password: '',
    fname: 'Rishi',
    lname: 'Jagariya',
    dob: '',
    phoneNo: '9582346634',
    courseOffered: []
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
      .loadTrainerCourses(this.trainerData.username)
      .subscribe((data) => { this.courseList = data})
  }

  editCourse() {
    
  }

  deleteCourse() {

  }

  logout() {

  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';
import { TrainerData } from '../trainerData';
import { UsernameAndCourse } from '../usernameAndCourse';

@Component({
  selector: 'trainerprofile',
  templateUrl: './trainerprofile.component.html',
  styleUrls: ['./trainerprofile.component.css']
})
export class TrainerprofileComponent implements OnInit {
  constructor( public restApi: ElpServiceService, public router: Router ) { }

  searchText : string = ''
  trainerData : TrainerData = {
    userId: 26,
    userType: 'trainer',
    username: 'rishabhajgariya',
    password: '',
    fname: 'Rishi',
    lname: 'Jagariya',
    dob: '',
    phoneNo: '9582346634',
    courseOffered: []
  }
  courseList : Course[] = []
    
  ngOnInit(): void {
    this.loadCourses()
  }

  //form search course
  submitForm(fromData : any) {

  }

  loadCourses() {
    return this.restApi
      .loadTrainerCourses(this.trainerData.username)
      .subscribe((data) => { console.log(data); this.courseList = data})
  }

  editCourse(course : Course) {
    console.log("im here in edit course")
    var username : string = this.trainerData.username
    this.restApi.trainerUpdateCourseData = {
      course: course,
      username: username
    }
    console.log(this.restApi.trainerUpdateCourseData)
    this.router.navigate(['/updatecourse'])
  }

  deleteCourse(courseId : Number) {
      var username : string = this.trainerData.username
      console.log(courseId + " " + username)
    return this.restApi.deleteCourse(username, courseId).subscribe(data => {
      console.log("deleted")
    })
  }

  logout() {

  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';
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
  constructor( public restApi: ElpServiceService, public router: Router , private authService : AuthService) { }

  searchText : string = ''
  trainerData : TrainerData = {
    userId: 0,
    userType: '',
    username: '',
    password: '',
    fname: '',
    lname: '',
    dob: '',
    phoneNo: '',
    courseOffered: []
  }
  courseList : Course[] = []
    
  ngOnInit(): void {
    this.trainerData = this.authService.getTrainer()
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
      this.loadCourses()
    })
  }

  logout() {
    this.authService.logout()
    this.router.navigate(['/login'])
  }

}

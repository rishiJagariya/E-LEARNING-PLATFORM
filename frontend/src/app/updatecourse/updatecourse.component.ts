import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';

import { ElpServiceService } from '../elp-service.service';
import { UsernameAndCourse } from '../usernameAndCourse';

@Component({
  selector: 'updatecourse',
  templateUrl: './updatecourse.component.html',
  styleUrls: ['./updatecourse.component.css']
})
export class UpdatecourseComponent implements OnInit {

  @Input()
  updateCourse : Course = {
    courseId : 1,
    courseName : 'Java',
    description : 'Java is a programming language',
    category : 'IT',
    fee: 4000,
    duration: 100,
    rating : 8,
    trainerId : 101
  }

  constructor(public restApi: ElpServiceService, public router: Router) { }

  ngOnInit(): void {
    this.updateCourse = this.restApi.trainerUpdateCourseData.course
  }

  submitForm(formData : any) {
    if(formData.valid){
      if(this.updateCourse.trainerId == 0)
      {
        alert("wrong trainer id");
        this.router.navigate(['/trainerprofile'])
      } else {
        console.log(this.updateCourse)
        var updateCourseData : UsernameAndCourse = {
          course : this.updateCourse,
          username : this.restApi.trainerUpdateCourseData.username
        }
        this.restApi
          .updateCourse(updateCourseData)
          .subscribe(data => {
            this.router.navigate(['/trainerprofile'])
            alert("course updated sccessfully")
          })
      }
    } else {
      alert("fields are empty")
    }  
  }
}

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
    courseId : 0,
    courseName : '',
    description : '',
    category : '',
    fee: 0,
    duration: 0,
    rating : 0,
    trainerId : 0
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

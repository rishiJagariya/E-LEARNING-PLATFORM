import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { ElpServiceService } from '../elp-service.service';

@Component({
  selector: 'updatecourse',
  templateUrl: './updatecourse.component.html',
  styleUrls: ['./updatecourse.component.css']
})
export class UpdatecourseComponent implements OnInit {

  @Input()
  updateCourse = {
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
  }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.updateCourse)
      this.restApi
        .updateCourse(this.updateCourse)
        .subscribe(data => {
          this.router.navigate(['/trainerprofile'])
        })
    } else {
      alert("fields are empty")
    }  
  }
}

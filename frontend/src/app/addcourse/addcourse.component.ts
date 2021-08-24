import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { ElpServiceService } from '../elp-service.service';

@Component({
  selector: 'addcourse',
  templateUrl: './addcourse.component.html',
  styleUrls: ['./addcourse.component.css']
})
export class AddcourseComponent implements OnInit {

  @Input()
  newCourse = {
    courseId : 1,
    courseName : '',
    description : '',
    category : '',
    fee: 0,
    duration: 0,
    rating : 8,
    trainerId : 101
  }

  constructor(public restApi: ElpServiceService, public router: Router) {}

  ngOnInit(): void {

  }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.newCourse)
      this.restApi
        .createCourse(this.newCourse)
        .subscribe(data => {
          this.router.navigate(['/trainerprofile'])
        })
    } else {
      alert("fields are empty")
    }  
  }
}

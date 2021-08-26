import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';

import { ElpServiceService } from '../elp-service.service';

@Component({
  selector: 'addcourse',
  templateUrl: './addcourse.component.html',
  styleUrls: ['./addcourse.component.css']
})
export class AddcourseComponent implements OnInit {

  @Input()
  newCourse = {
    courseId : 0,
    courseName : '',
    description : '',
    category : '',
    fee: 0,
    duration: 0,
    rating : 0,
    trainerId : 0
  }

  constructor(public restApi: ElpServiceService, public router: Router, private authService : AuthService) {}

  ngOnInit(): void {
    this.newCourse.trainerId = this.authService.getTrainer().trainerId
  }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.newCourse)
      this.restApi
        .createCourse(this.newCourse)
        .subscribe(data => {
          if(data.message == "Success")
            this.router.navigate(['/trainerprofile'])
          else
            alert("some error occured")
        })
    } else {
      alert("fields are empty")
    }  
  }
}

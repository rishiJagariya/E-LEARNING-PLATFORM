import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';

import { ElpServiceService } from '../elp-service.service';


@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  @Input()
  newUser = {
    userType : '',
    fname : '',
    lname : '',
    dob : '',
    username : '',
    password : '',
    confirmPassword : '',
    phoneNo : ''
  }

  constructor(public restApi: ElpServiceService, public router: Router,  private authService : AuthService) {

  }
  ngOnInit(): void {

    if(this.authService.isLoggedIn()){
      if(this.authService.getRole() == "student")
        this.router.navigate(['/home'])
      else
        this.router.navigate(['/trainerprofile'])
    }

    //tried to add tooltips using jquery but not worked
    // $(document).ready(function() { 
    //   (<any>$('[data-toggle="tooltip"]')).tooltip(); 
    // }); 
  }
  
  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.newUser)
      this.restApi
        .createUser(this.newUser)
        .subscribe(data => {
          console.log(data)
          this.router.navigate(['/login'])
        })
    } else {
      alert("fields are empty")
    }  
  }
}

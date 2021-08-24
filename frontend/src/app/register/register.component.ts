import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(public restApi: ElpServiceService, public router: Router) {

  }
  ngOnInit(): void {

    //tried to add tooltips using jquery but not worked
    // $(document).ready(function() { 
    //   (<any>$('[data-toggle="tooltip"]')).tooltip(); 
    // }); 
  }
  
  // checkPassword() {
  //     console.log("inside checkpassword");
  //     this.newUser.password = (<HTMLInputElement>document.getElementById("password")).value;
  //     this.newUser.confirmPassword = (<HTMLInputElement>document.getElementById("confirmpassword")).value;
  //     if(this.newUser.password == "" || this.newUser.confirmPassword == ""){
  //       return false
  //     }
  //     if(this.newUser.password === this.newUser.confirmPassword) {
  //       return true 
  //     }else{
  //       return false
  //     }
  // }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.newUser)
      this.restApi
        .createUser(this.newUser)
        .subscribe(data => {
          this.router.navigate(['/login'])
        })
    } else {
      alert("fields are empty")
    }  
  }
}

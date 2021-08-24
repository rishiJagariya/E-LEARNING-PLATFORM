import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  @Input()
  forgotPasswordDetails = {
    userType : '',
    username : '',
    password : '',
    confirmPassword : ''
  }
  constructor() { }

  ngOnInit(): void {
  }

  checkPassword() {
    console.log("inside checkpassword");
    this.forgotPasswordDetails.password = (<HTMLInputElement>document.getElementById("password")).value;
    this.forgotPasswordDetails.confirmPassword = (<HTMLInputElement>document.getElementById("confirmpassword")).value;
    if(this.forgotPasswordDetails.password == "" || this.forgotPasswordDetails.confirmPassword == ""){
      alert("password can't be empty")
      return false
    }
    if(this.forgotPasswordDetails.password === this.forgotPasswordDetails.confirmPassword) {
      return true 
    }else {
      alert("password are not same")
      return false
    }
  }

  submitForm() {
    
  }
  
}

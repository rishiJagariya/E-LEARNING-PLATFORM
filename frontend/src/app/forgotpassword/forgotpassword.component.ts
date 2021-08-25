import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ElpServiceService } from '../elp-service.service';

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
  constructor(public restApi: ElpServiceService, public router: Router) { }

  ngOnInit(): void {
  }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.forgotPasswordDetails)
      this.restApi
        .forgotPassword(this.forgotPasswordDetails)
        .subscribe(data => {
          console.log(data)
          this.router.navigate(['/login'])
        })
    } else {
      alert("fields are empty")
    }  
  }
  
}

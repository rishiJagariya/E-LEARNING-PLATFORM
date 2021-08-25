import { INFERRED_TYPE } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ElpServiceService } from '../elp-service.service';
//import { NgserviceService } from

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input()
  userLoginInfo = {
    userType : '',
    username : '',
    password : '',
  }

  constructor(public restApi: ElpServiceService, public router: Router) { }

  ngOnInit(): void {
  }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.userLoginInfo)
      let response = this.restApi.userLogin(this.userLoginInfo)
      response.subscribe(data => {
        console.log(data)
        
        if(data.message == "Success"){
          if(this.userLoginInfo.userType == "student")
            this.router.navigate(['/home'])
          else
            this.router.navigate(['/trainerprofile'])
          sessionStorage.setItem("username",this.userLoginInfo.username)
          sessionStorage.setItem("userType", this.userLoginInfo.userType)
        } else {
          this.router.navigate(['/login'])
          alert("invalid username or password")
        }
      })
    } else {
      alert("fields are empty")
    }
  }

}

import { INFERRED_TYPE } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';
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

  constructor(public restApi: ElpServiceService, public router: Router, private authService : AuthService) { }

  ngOnInit(): void {
  }

  submitForm(formData : any) {
    if(formData.valid){
      console.log(this.userLoginInfo)
      let response = this.restApi.userLogin(this.userLoginInfo)
      response.subscribe(data => {
        console.log(data)
        if(data.message == "Success"){
          if(data.userType == "student"){
            this.authService.login(data.userType, data.username)
            this.router.navigate(['/home'])

          }
          else if(data.userType == "trainer"){
            console.log("im here into login as trainer - 1")
            this.authService.login(data.userType, data.username)
            console.log("im here into login as trainer - 2")
            this.router.navigate(['/trainerprofile'])
          }
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

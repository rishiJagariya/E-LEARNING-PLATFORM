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
          if(this.userLoginInfo.userType == "student"){
            this.authService.login("student")
            this.router.navigate(['/home'])
          }
          else if(this.userLoginInfo.userType == "trainer"){
            this.authService.login("trainer")
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

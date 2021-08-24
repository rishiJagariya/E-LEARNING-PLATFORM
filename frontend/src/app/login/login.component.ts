import { INFERRED_TYPE } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
  }

  submitForm() {

  }

}

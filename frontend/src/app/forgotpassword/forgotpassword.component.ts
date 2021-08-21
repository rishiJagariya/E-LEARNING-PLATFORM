import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  mustmatch(formGroup: FormGroup) {
    const  password = formGroup.get('password');
    const confirmPassword  = formGroup.get('confirmpassword');
    if(password === confirmPassword)
      return false;
    else
      return true;
  }
  
}

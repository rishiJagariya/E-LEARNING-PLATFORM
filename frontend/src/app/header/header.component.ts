import { Component, OnInit } from '@angular/core';
import { AuthService } from '../AuthService';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( private authService : AuthService) { }

  ngOnInit(): void {
  }

  isStudent() {
    return this.authService.getRole() == "student" ? true : false
  }

}

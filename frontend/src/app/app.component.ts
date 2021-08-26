import { Component, OnInit } from '@angular/core';
import { AuthService } from './AuthService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  constructor(private authService : AuthService) {
  }
  ngOnInit(): void {
    // localStorage.clear();
  }

  isLoggedIn() {
    return this.authService.isLoggedIn()
  }
}

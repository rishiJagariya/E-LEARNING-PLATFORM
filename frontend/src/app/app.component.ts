import { Component } from '@angular/core';
import { AuthService } from './AuthService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  constructor(private authService : AuthService) {
  
  }

  isLoggedIn() {
    return this.authService.isLoggedIn()
  }
}

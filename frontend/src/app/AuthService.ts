import { Injectable } from "@angular/core";
import { of } from "rxjs";
import { ElpServiceService } from "./elp-service.service";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  isLogin = false
  currentUsername : string
  roleAs: string

  constructor(public restApi: ElpServiceService) { 
    this.roleAs = ''
    this.currentUsername = ''
  }
 
  login(value: string, username : string) {
    this.isLogin = true;
    this.roleAs = value;
    this.currentUsername = username;
    localStorage.setItem('STATE', 'true');
    localStorage.setItem('ROLE', this.roleAs);
    localStorage.setItem('USER', this.currentUsername);
    if(this.roleAs === 'trainer'){
      this.restApi.getTrainer(this.currentUsername).subscribe(data => {
        console.log(data)
        console.log(JSON.stringify(data))
        localStorage.setItem('USERINFO', JSON.stringify(data))
        console.log('im here into authService trainer login')
      })
    } else if(this.roleAs === 'student'){
      this.restApi.getStudent(this.currentUsername).subscribe(data => {
        localStorage.setItem('USERINFO', JSON.stringify(data))
      })
    }
    return of({ success: this.isLogin, role: this.roleAs });
  }

  logout() {
    this.isLogin = false;
    this.roleAs = '';
    this.currentUsername = ''
    localStorage.setItem('STATE', 'false');
    localStorage.setItem('ROLE', '');
    localStorage.setItem('USER', '');
    localStorage.setItem('USERINFO', '');
    return of({ success: this.isLogin, role: '' });
  }

  isLoggedIn() {
    const loggedIn = localStorage.getItem('STATE');
    if (loggedIn == 'true')
      this.isLogin = true;
    else
      this.isLogin = false;
    return this.isLogin;
  }

  //setTimeout( , 50); 
  
  getTrainer() {
    let data = localStorage.getItem('USERINFO')

    if(data == null){
      console.log('its really null')
      return null
    } else {
      console.log('no its not null')
      console.log(data)
      return JSON.parse(data)
    } 
  }

  getStudent() {
    const data = localStorage.getItem('USERINFO')
    if(data == null){
      return null
    } else {
      return JSON.parse(data)
    } 
  }

  getRole() {
    const role =  localStorage.getItem('ROLE')
    if(role === null) {
      this.roleAs = ''
    } else {
      this.roleAs = role
    }
    return localStorage.getItem('ROLE')
  }

}
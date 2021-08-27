import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../AuthService';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';
import { StudentData } from '../studentData';
import { UserFetched } from '../userFetched';

@Component({
  selector: 'cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor( public restApi: ElpServiceService, public router: Router, private authService : AuthService ) { } 

  name : String = ''  
  studentId : Number = 0
  totalSum : Number = 0
  discount : Number = 20
  finalPrice : Number = 0

  cartItems : Course[] = []

  ngOnInit(): void {
    //var studentInfo : StudentData = this.authService.getStudent()
    var userInfo : UserFetched =  this.authService.getStudent()
    this.name = userInfo.fname
    this.studentId = userInfo.userId
    this.getCartItems(this.studentId)
    //this.checkForDiscount()
  }

  getCartItems(userId : Number) {
    console.log("im here in view cart")
    return this.restApi.getCartItems(userId)
      .subscribe(data => {
          console.log(data)
          this.cartItems = data
          this.updateTotalSum()
      })
  }

  deleteCart(courseId : Number) {
    console.log(courseId)
    return this.restApi.deleteCart(courseId, this.studentId)
      .subscribe(data => {
        console.log(data.message)
        this.getCartItems(this.studentId)
      })
  }

  updateTotalSum() {
    //2 ways - one to fetch from backend and second way is to calculate here
    var sum : Number = 0
    this.cartItems.forEach(element => {
      sum = sum.valueOf() + element.fee.valueOf()
    });
    this.totalSum = sum
    this.checkForDiscount()
  }

  checkout() {
    //remove everything from cart and sent it to enroll[] list of student (**or add also in Enrollment table)
    return this.restApi.checkout(this.studentId)
      .subscribe(data => {
        console.log(data.message)
        if(data.message == "Success"){
          this.router.navigate(['/studentprofile'])
        }
        else {
          alert("some error occured")
        }
      })
  }

  checkForDiscount(){
    if(this.isOfferTime())
      this.finalPrice = this.totalSum.valueOf() * 0.8
  }

  isOfferTime() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    console.log(dd);
    if(dd == '26' || dd == '25'){
      return true
    }
    return false
  }
}
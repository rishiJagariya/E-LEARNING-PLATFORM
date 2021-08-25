import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import { ElpServiceService } from '../elp-service.service';

@Component({
  selector: 'cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor( public restApi: ElpServiceService, public router: Router ) { } 

  name : String = 'Rishi'  //todo
  studentId : Number = 27
  totalSum : Number = 0

  cartItems : Course[] = [{
    courseId: 0,
    courseName: '',
    description: '',
    category: '',
    fee: 0,
    duration: 0,
    rating: 0,
    trainerId: 0
    },
  ]
  ngOnInit(): void {
    this.getCartItems(this.studentId)
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
}
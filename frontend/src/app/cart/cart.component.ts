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
  courseList : Course[] = []
  total(){

  }

  ngOnInit(): void {
   // this.getCartItems(userId : Number)
  }

  getCartItems(userId : Number) {
    console.log("im here in view cart")
    return this.restApi.getCartItems(userId)
      .subscribe(data => {
          console.log(data)
          this.cartItems = data
      })
  }
  deleteCart(courseId : Number) {
    console.log(courseId)
  }
}




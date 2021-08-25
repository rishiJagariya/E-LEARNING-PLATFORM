import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor() { }
  products={
    Courseid:'',
    Coursename:'',
    Catagory:'',
    Price:'',
  } ;
  
  rows = [
    {
      "Courseid" : "1",
      "Coursename" : "JAVA",
      "Catagory" : "CS",
       "Price":"200",
    },
    {
      "Courseid" : "2",
      "Coursename" : "C++",
      "Catagory" : "CS",
      "Price":"500",
      
    },
    {
      "Courseid" : "3",
      "Coursename" : "Applied Physics",
      "Catagory" : "EEE",
      "Price":"200"

    },
    {
      "Courseid" : "4",
      "Coursename" : "Electronic Devices and Circuits",
      "Catagory" : "EEE",

    },

  ]
  ngOnInit(): void {
  }
 
}

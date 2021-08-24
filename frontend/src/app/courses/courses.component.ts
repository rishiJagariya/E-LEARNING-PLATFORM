import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  rows = [
    {
      "Courseid" : "1",
      "Coursename" : "JAVA",
      "Catagory" : "CS",

    },
    {
      "Courseid" : "2",
      "Coursename" : "C++",
      "Catagory" : "CS",
      
    },
    {
      "Courseid" : "3",
      "Coursename" : "Applied Physics",
      "Catagory" : "EEE",

    },
    {
      "Courseid" : "4",
      "Coursename" : "Electronic Devices and Circuits",
      "Catagory" : "EEE",

    },

  ]
  
}

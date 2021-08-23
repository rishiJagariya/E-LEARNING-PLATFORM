import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'studentprofile',
  templateUrl: './studentprofile.component.html',
  styleUrls: ['./studentprofile.component.css']
})
export class StudentprofileComponent implements OnInit {
  name='Ritika';
  Title='Student';
  constructor() { }
  Student={
    Courseid:'',
    Coursename:'',
    Catagory:'',
  } ;
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
  ngOnInit(): void {
  }

}

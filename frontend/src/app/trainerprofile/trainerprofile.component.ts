import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'trainerprofile',
  templateUrl: './trainerprofile.component.html',
  styleUrls: ['./trainerprofile.component.css']
})
export class TrainerprofileComponent implements OnInit {
  name='Ritika';
  Title='Trainer';
  constructor(
  ) {
  }
  trainer={
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

package com.elp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elp.service.StudentService;
import com.elp.service.TrainerService;
import com.elp.entity.User;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ELPRestController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@PostMapping("createuser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
	
		String message = null;
		if (user.getUserType().equals("trainer")) {
			System.out.println("hello");
			Trainer newTrainer = new Trainer(user);
			System.out.println("Into Create user() 2 - " + newTrainer);
			message = trainerService.createTrainer(newTrainer);
			System.out.println(message);
		} else if (user.getUserType().equals("student")) {
			Student newStudent = new Student(user);
			message = studentService.createStudent(newStudent);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("userlogin")
	public ResponseEntity<String> userLogin()
	{
		return null;
	}

	@PostMapping("forgotpassword")
	public String forgotPassword()
	{
		return null;
	}
}

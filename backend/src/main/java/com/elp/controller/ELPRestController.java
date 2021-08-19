package com.elp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elp.service.StudentService;
import com.elp.service.TrainerService;
import com.elp.entity.User;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

@RestController
public class ELPRestController {
	@Autowired
	StudentService studentService;

	@Autowired
	TrainerService trainerService;

	@CrossOrigin
	@PostMapping("createuser")
	public ResponseEntity<String> createUser(@RequestBody User user) {

		if (user.getUserType() == "trainer") {
			Trainer newTrainer = new Trainer(user);
			trainerService.createTrainer(newTrainer);

		} else if (user.getUserType() == "student") {
			Student newStudent = new Student(user);
			studentService.createStudent(newStudent);
		}
		return null;
	}

	@PostMapping("userlogin")
	public ResponseEntity<String> userLogin(@RequestParam(name = "userType") String userType, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		String message;

		if (userType == "trainer") {
			Trainer trainer = trainerService.getTrainerByUsername(username);
			
			if (trainer == null) {
				message = "Username is incorrect";
				
			} else if (trainer.getPassword() == password) {
				message = "User logged in successfully";
				
			} else {
				message = "Password is incorrect";
			}

		} else if (userType == "student") {
			Student student = studentService.getStudentByUsername(username);
			
			if (student == null) {
				message = "Username is incorrect";
				
			} else if (student.getPassword() == password) {
				message = "User logged in successfully";
				
			} else {
				message = "Password is incorrect";
			}
		}
		return null;
	}

	@PostMapping("forgotpassword")
	public String forgotPassword(@RequestParam(name = "userType") String userType, @RequestParam(name = "username") String username)
	{
		String message;
		
		if(userType == "trainer") {
			Trainer trainer = trainerService.getTrainerByUsername(username);
			
			if(trainer == null) {
				message = "Username is incorrect";
				
			} else if() {
				String pass;
				trainer.setPassword(pass);
				message = "Password created successfully";
			}
			
		} else if(userType == "student") {		
			Student student = studentService.getStudentByUsername(username);
			
			if(student == null) {
				message = "Username is incorrect";
			} else if(student.getPassword() == password) {
				String pass;
				student.setPassword(pass);
				message = "Password created successfully";
			}
		}
		return null;
	}
}

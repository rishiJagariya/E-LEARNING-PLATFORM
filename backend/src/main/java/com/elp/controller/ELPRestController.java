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

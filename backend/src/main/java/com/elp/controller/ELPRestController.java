package com.elp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elp.service.StudentService;
import com.elp.service.TrainerService;

import com.elp.entity.User;
import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

@RestController()
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ELPRestController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@PostMapping("/createuser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		System.out.println("Im here - 001");
	
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

	@PostMapping("/userlogin")
	public ResponseEntity<String> userLogin(@RequestBody MultiValueMap<String, String> formData)
	{
    	System.out.println("Into UserLogin Controller");
    	String message = null;
    	String username = formData.getFirst("username");
    	String password = formData.getFirst("password");
    	String userType = formData.getFirst("usertype");
   
        if (userType.equals("trainer")) {
            Trainer trainer = trainerService.getTrainerByUsername(username);
           
            if (trainer == null)
                message = "Username is incorrect"; 
            else if (trainer.getPassword().equals(password))
                message = "User logged in successfully";  
            else 
                message = "Password is incorrect";
        	
//        	HttpSession oldSession = HttpSession.getSession(false);
//        	
//        	if(oldSession != null) {
//        		oldSession.invalidate();
//        	}
//        	HttpSession newSession = request.getSession(true);
//        	
        	//TODO: save the session & token(JWT) to Cookies (client)
        	
        } else if (userType.equals("student")) {
        	
          	System.out.println("i'm here - student ");
            Student student = studentService.getStudentByUsername(username);
            
            if (student == null)
                message = "Username is incorrect";
            else if (student.getPassword().equals(password))
                message = "User logged in successfully"; 
            else
                message = "Password is incorrect";
        	
        	System.out.println(student);
        }
        
        return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<String> forgotPassword(@RequestBody MultiValueMap<String, String> formData)
	{
		System.out.println("Into ForgotPassword Controller");
    	String message = null;
    	String username = formData.getFirst("username");
    	String newPassword = formData.getFirst("password");
    	String userType = formData.getFirst("usertype");
   
        if (userType.equals("trainer")) {
            Trainer trainer = trainerService.getTrainerByUsername(username);
           
            if (trainer == null)
                message = "Username is incorrect";  
            else {
            	trainer.setPassword(newPassword);
            	trainerService.updatePassword();
                message = "Password changed successfully";
            }
        	
        } else if (userType.equals("student")) {
            Student student = studentService.getStudentByUsername(username);
            
            if (student == null)
                message = "Username is incorrect";
            else {
            	student.setPassword(newPassword);
            	studentService.updatePassword();
                message = "Password changed successfully";
            }
        }
        
        return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping("/addcourse")
	public ResponseEntity<String> addCourse(@RequestBody Course course) {
		System.out.println("Im here in add course");
		String message = null;
		
		message = trainerService.createCourse(course);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping("/updatecourse")
	public ResponseEntity<String> updateCourse(@RequestBody String username, Course course) {
		System.out.println("Im here in update course");
		String message = null;
		
		message = trainerService.updateCourse(username, course);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping("/deletecourse")
	public ResponseEntity<String> deleteCourse(@RequestBody String username, int courseId) {
		System.out.println("Im here in delete course");
		String message = null;
		
		message = trainerService.deleteCourse(username, courseId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/trainercourselist")
	public ResponseEntity<ArrayList<Course>> getTrainerCoursesList(String username) {
		System.out.println("Im here in trainer course list");
		String message = null;
		
		ArrayList<Course> coursesByTrainer= trainerService.deleteCourse(username, courseId);
		message = "list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<ArrayList<Course>>(coursesByTrainer, HttpStatus.OK);
	}
}

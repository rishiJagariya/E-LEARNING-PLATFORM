package com.elp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class UserController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@PostMapping("/createuser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		System.out.println("Im here in create user");	
		String message = null;
		
		if (user.getUserType().equals("trainer")) {
			System.out.println("hello trainer");
			Trainer newTrainer = new Trainer(user);
			
			System.out.println("Into Create user() 2 - " + newTrainer);
			
			message = trainerService.createTrainer(newTrainer);
			System.out.println(message);
			
		} else if (user.getUserType().equals("student")) {
			System.out.println("hello student");
			Student newStudent = new Student(user);
			
			message = studentService.createStudent(newStudent);
			System.out.println(message);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		System.out.println("Im here in update user");	
		String message = null;
		
		if (user.getUserType().equals("trainer")) {
			System.out.println("hello trainer");
			Trainer newTrainer = new Trainer(user);
			
			message = trainerService.updateTrainer(newTrainer);
			System.out.println(message);
			
		} else if (user.getUserType().equals("student")) {
			System.out.println("hello student");
			Student newStudent = new Student(user);
			
			message = studentService.updateStudent(newStudent);
			System.out.println(message);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletetrainer")
	public ResponseEntity<String> deleteTrainer(int userId) {
		System.out.println("Im here in delete trainer");
		String message = null;
		
		message = trainerService.deleteTrainer(userId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletestudent")
	public ResponseEntity<String> deleteStudent(int userId) {
		System.out.println("Im here in delete student");
		String message = null;
		
		message = studentService.deleteStudent(userId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
//	@DeleteMapping
//	public ResponseEntity<String> deleteUser(@RequestParam User user) {
//		System.out.println("I am here in delete user");	
//		String message = null;
//		
//		if (user.getUserType().equals("trainer")) {
//			System.out.println("hello trainer");
//			Trainer newTrainer = new Trainer(user);
//			
//			message = trainerService.deleteTrainer(newTrainer);
//			System.out.println(message);
//			
//		} else if (user.getUserType().equals("student")) {
//			System.out.println("hello student");
//			Student newStudent = new Student(user);
//			
//			message = studentService.deleteStudent(newStudent);
//			System.out.println(message);
//		}
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}

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
            	trainerService.updatePassword(username, newPassword);
                message = "Password changed successfully";
            }
        	
        } else if (userType.equals("student")) {
            Student student = studentService.getStudentByUsername(username);
            
            if (student == null)
                message = "Username is incorrect";
            else {
            	student.setPassword(newPassword);
            	studentService.updatePassword(username, newPassword);
                message = "Password changed successfully";
            }
        }
        
        return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	// TODO
	@PostMapping("/userlogout")
	public ResponseEntity<String> userLogout(@RequestBody String username)
	{
		return null;
	}
}

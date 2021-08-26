package com.elp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.elp.model.wrapper.LoginData;
import com.elp.model.wrapper.ResponseMsgObject;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

/**
 * @author Rishi Jagariya
 *
 */
@RestController()
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@PostMapping("/createuser")
	public ResponseEntity<ResponseMsgObject> createUser(@RequestBody User user) {
		System.out.println("Im here in create user");	
		String message = null;
		if(user.getUserType().equals("trainer")) {
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
		ResponseMsgObject res = new ResponseMsgObject(message, user.getUserType(), user.getUsername());
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
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
	public ResponseEntity<String> deleteTrainer(@RequestParam int userId) {
		System.out.println("Im here in delete trainer");
		String message = null;
		
		message = trainerService.deleteTrainer(userId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletestudent/{userId}")
	public ResponseEntity<String> deleteStudent(@PathVariable int userId) {
		System.out.println("Im here in delete student");
		String message = null;
		
		message = studentService.deleteStudent(userId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	

	@PostMapping("/userlogin")
	public ResponseEntity<ResponseMsgObject> userLogin(@RequestBody LoginData loginData)
	{
    	System.out.println("Into UserLogin Controller" + loginData);
    	String message = null;
    	String username = loginData.getUsername();
    	String password = loginData.getPassword();
    	String userType = loginData.getUserType();
   
        if (userType.equals("trainer")) {
            Trainer trainer = trainerService.getTrainerByUsername(username);
            if (trainer == null)
                message = "UsernameError"; 
            else if (trainer.getPassword().equals(password))
                message = "Success";  
            else 
                message = "PasswordError";    
         	
        } else if (userType.equals("student")) {
          	System.out.println("i'm here in student login ");
            Student student = studentService.getStudentByUsername(username);
            if (student == null)
                message = "UsernameError";
            else if (student.getPassword().equals(password))
                message = "Success"; 
            else
                message = "PasswordError";
        	
        	System.out.println(message + student);
        }
        ResponseMsgObject res = new ResponseMsgObject(message, userType, username);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getTrainer/{username}")
	public ResponseEntity<User> getTrainer(@PathVariable String username)
	{
    	System.out.println("Into getTrainer Controller" + username);
        Trainer trainer = trainerService.getTrainerByUsername(username);
        User loggedUser = new User(trainer);
        
        return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}
	
	@GetMapping("/getStudent/{username}")
	public ResponseEntity<User> getStudent(@PathVariable String username)
	{
    	System.out.println("Into getTrainer Controller" + username);
        Student student = studentService.getStudentByUsername(username);
        User loggedUser = new User(student);
     
        return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}
	

	@PostMapping("/forgotpassword")
	public ResponseEntity<ResponseMsgObject> forgotPassword(@RequestBody LoginData data)
	{
		System.out.println("Into UserLogin Controller" + data);
    	String message = null;
    	String username = data.getUsername();
    	String password = data.getPassword();
    	String userType = data.getUserType();
   
        if (userType.equals("trainer")) {
            Trainer trainer = trainerService.getTrainerByUsername(username);
            if (trainer == null)
                message = "UsernameError";  
            else {
            	trainerService.updatePassword(username, password);
                message = "Success";
            }	
        } else if (userType.equals("student")) {
            Student student = studentService.getStudentByUsername(username);
            if (student == null)
                message = "UsernameError";
            else {
            	studentService.updatePassword(username, password);
                message = "Success";
            }
        }
        
        ResponseMsgObject res = new ResponseMsgObject(message, userType, username);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	// TODO
	@PostMapping("/userlogout")
	public ResponseEntity<String> userLogout(@RequestBody String username)
	{
		return null;
	}
}

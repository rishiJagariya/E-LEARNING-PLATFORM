package com.elp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.service.StudentService;
import com.elp.service.TrainerService;

@RestController()
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CartController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@PostMapping("/enrollToCourse")
	public ResponseEntity<String> enrollToCourse(@RequestBody int userId,int courseId) {
		System.out.println("Im here in enroll to course");
		String message = null;
		
		message = studentService.enroll(userId, courseId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/unenrollFromCourse")
	public ResponseEntity<String> unenrollFromCourse(@RequestBody int enrollId) {
		System.out.println("Im here in unenroll from course");
		String message = null;
		
		message = studentService.unEnroll(enrollId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping("/addToCart")
	public ResponseEntity<String> addToCart(Cart cart) {
		System.out.println("Im here in add to cart");
		String message = null;
		
		message = studentService.addToCart(cart);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeFromCart")
	public ResponseEntity<String> removeFromCart(int courseId) {
		System.out.println("Im here in remove from cart");
		String message = null;
		
		message = studentService.removeFromCart(courseId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("/viewCart")
	public ResponseEntity<List<Course>> viewCart(int userId) {
		System.out.println("Im here in view cart");
		String message = null;
		
		List<Course> cartView = studentService.viewCart(userId);
		message = "cart viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(cartView, HttpStatus.OK);
	}
}
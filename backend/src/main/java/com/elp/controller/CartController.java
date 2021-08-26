package com.elp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.model.wrapper.ResponseMsgObject;
import com.elp.service.StudentService;
import com.elp.service.TrainerService;

/**
 * @author Ritika
 *
 */
@RestController()
@RequestMapping(value = "/cart")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CartController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@GetMapping("/checkout/{userId}")
	public ResponseEntity<ResponseMsgObject> enrollToCourse(@PathVariable int userId) {
		System.out.println("Im here in enroll to course" + userId);
		String message = studentService.enroll(userId);
		System.out.println(message);
		Student student = studentService.getStudentById(userId);
		
		ResponseMsgObject res = new ResponseMsgObject(message, "student", student.getUsername());
		System.out.println(res);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/unenrollFromCourse/{userId}/{courseId}")
	public ResponseEntity<ResponseMsgObject> unenrollFromCourse(@PathVariable int userId,@PathVariable int courseId) {
		System.out.println("Im here in unenroll from course");
		String message = studentService.unEnroll(userId,courseId);
		Student student = studentService.getStudentById(userId);
		
		ResponseMsgObject res = new ResponseMsgObject(message, "student", student.getUsername());
		System.out.println(res);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@GetMapping("/addToCart/{courseId}/{userId}")
	public ResponseEntity<ResponseMsgObject> addToCart(@PathVariable("courseId") int courseId,@PathVariable("userId") int userId) {
		System.out.println("Im here in add to cart" + courseId);
		String message = studentService.addToCart(courseId,userId);
		Student student = studentService.getStudentById(userId);
		System.out.println(message);
		
		ResponseMsgObject res = new ResponseMsgObject(message, "student", student.getUsername());
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeFromCart/{courseId}/{studentId}")
	public ResponseEntity<ResponseMsgObject> removeFromCart(@PathVariable("courseId") int courseId,@PathVariable("studentId") int studentId) {
		System.out.println("Im here in remove from cart");
		String message = studentService.removeFromCart(courseId,studentId);
		Student student = studentService.getStudentById(studentId);
		System.out.println(message);
		
		ResponseMsgObject res = new ResponseMsgObject(message, "student", student.getUsername());
		System.out.println(res);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@GetMapping("/viewCart/{userId}")
	public ResponseEntity<List<Course>> viewCart(@PathVariable int userId) {
		System.out.println("Im here in view cart");
		String message = null;
		
		List<Course> cartView = studentService.viewCart(userId);
		message = "cart viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(cartView, HttpStatus.OK);
	}
}

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;
import com.elp.model.wrapper.ResponseMsgObject;
import com.elp.model.wrapper.UsernameAndCourse;
import com.elp.service.StudentService;
import com.elp.service.TrainerService;

/**
 * @author Ritika
 *
 */
@RestController()
@RequestMapping(value = "/course")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CourseController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;
	@PostMapping("/createcourse")
	public ResponseEntity<ResponseMsgObject> createCourse(@RequestBody Course course) {
		System.out.println("Im here in create course");
		String message = null;
		
		message = trainerService.createCourse(course);
		System.out.println(message);
		Trainer trainer = trainerService.getTrainerById(course.getTrainerId());
		
        ResponseMsgObject res = new ResponseMsgObject(message, "trainer", trainer.getUsername());
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@PutMapping("/updatecourse")
	public ResponseEntity<ResponseMsgObject> updateCourse(@RequestBody UsernameAndCourse usernameAndCourse) {
		String username = usernameAndCourse.getUsername();
		Course course = usernameAndCourse.getCourse();
		System.out.println(username + " " + course);
		String message = null;
		
		message = trainerService.updateCourse(username, course);
		System.out.println(message);
		
		ResponseMsgObject res = new ResponseMsgObject(message, "trainer", username);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecourse/{username}/{courseId}")
	public ResponseEntity<ResponseMsgObject> deleteCourse(@PathVariable("username") String username, @PathVariable("courseId") int courseId) {
		System.out.println("Im here in delete course" + username + " " + courseId);
		String message = null;
		
		message = trainerService.deleteCourse(username, courseId);
		System.out.println(message);
		
		ResponseMsgObject res = new ResponseMsgObject(message, "trainer", username);
        return new ResponseEntity<ResponseMsgObject>(res, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getTrainerCourseList/{username}")
	public ResponseEntity<List<Course>> getTrainerCourseList(@PathVariable String username) {
		System.out.println("Im here in trainer course list");
		String message = null;
		
		List<Course> trainerCourses = trainerService.getTrainerCourseList(username);
		message = "trainer course list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(trainerCourses, HttpStatus.OK);
	}
	
	@GetMapping("/getStudentEnrollList/{courseId}")
	public ResponseEntity<List<Student>> getStudentEnrollList(@PathVariable int courseId) {
		System.out.println("Im here in student enroll list");
		String message = null;
		
		List<Student> studentsEnrolled = trainerService.getStudentEnrollList(courseId);
		message = "student enroll list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Student>>(studentsEnrolled, HttpStatus.OK);
	}
	
	@GetMapping("/getCourseList")
	public ResponseEntity<List<Course>> getCourseList(@RequestBody Course course) {
		System.out.println("Im here in course list");
		String message = null;
		
		List<Course> courses = studentService.getCourseList(course);
		message = "course list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("/getEnrolledCourseList/{userId}")
	public ResponseEntity<List<Course>> getEnrolledCourseList(@PathVariable int userId) {
		System.out.println("Im here in enrolled course list" + userId);
		String message = null;
		
		List<Course> coursesEnrolled = studentService.getEnrolledCourseList(userId);
		message = "Success";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(coursesEnrolled, HttpStatus.OK);
	}
	
	@GetMapping("/searchTrainerCourses/{username}/{courseName}")
	public ResponseEntity<List<Course>> searchTrainerCourses(@PathVariable String username, @PathVariable String courseName) {
		System.out.println("Im here in search trainer courses");
		String message = null;
		
		List<Course> trainerCourseSearched = trainerService.searchTrainerCourses(username);
		message = "trainer course searched successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(trainerCourseSearched, HttpStatus.OK);
	}
	
	@GetMapping("/searchCourses")
	public ResponseEntity<List<Course>> searchCourses(String courseName) {
		System.out.println("Im here in search all courses");
		String message = null;
		
		List<Course> courseSearched = studentService.searchCourses(courseName);
		message = "course searched successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(courseSearched, HttpStatus.OK);
	}
}

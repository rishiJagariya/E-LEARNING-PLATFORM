package com.elp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.service.StudentService;
import com.elp.service.TrainerService;

@RestController()
@RequestMapping(value = "/course")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "false",allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CourseController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;
	@PostMapping("/createcourse")
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
		System.out.println("Im here in create course");
		String message = null;
		
		message = trainerService.createCourse(course);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PutMapping("/updatecourse")
	public ResponseEntity<String> updateCourse(@RequestBody String username, Course course) {
		System.out.println("Im here in update course");
		String message = null;
		
		message = trainerService.updateCourse(username, course);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecourse")
	public ResponseEntity<String> deleteCourse(@RequestBody String username, int courseId) {
		System.out.println("Im here in delete course");
		String message = null;
		
		message = trainerService.deleteCourse(username, courseId);
		System.out.println(message);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/getTrainerCourseList")
	public ResponseEntity<List<Course>> getTrainerCourseList(String username) {
		System.out.println("Im here in trainer course list");
		String message = null;
		
		List<Course> trainerCourses = trainerService.getTrainerCourseList(username);
		message = "trainer course list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(trainerCourses, HttpStatus.OK);
	}
	
	@GetMapping("/getStudentEnrollList")
	public ResponseEntity<List<Student>> getStudentEnrollList(int courseId) {
		System.out.println("Im here in student enroll list");
		String message = null;
		
		List<Student> studentsEnrolled = trainerService.getStudentEnrollList(courseId);
		message = "student enroll list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Student>>(studentsEnrolled, HttpStatus.OK);
	}
	
	@GetMapping("/getCourseList")
	public ResponseEntity<List<Course>> getCourseList(Course course) {
		System.out.println("Im here in course list");
		String message = null;
		
		List<Course> courses = studentService.getCourseList(course);
		message = "course list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("/getEnrolledCourseList")
	public ResponseEntity<List<Course>> getEnrolledCourseList(int userId) {
		System.out.println("Im here in enrolled course list");
		String message = null;
		
		List<Course> coursesEnrolled = studentService.getEnrolledCourseList(userId);
		message = "course enroll list viewed successfully";
		System.out.println(message);
		
		return new ResponseEntity<List<Course>>(coursesEnrolled, HttpStatus.OK);
	}
	
	@GetMapping("/searchTrainerCourses")
	public ResponseEntity<List<Course>> searchTrainerCourses(String username, String courseName) {
		System.out.println("Im here in search trainer courses");
		String message = null;
		
		List<Course> trainerCourseSearched = trainerService.searchTrainerCourses(username, courseName);
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

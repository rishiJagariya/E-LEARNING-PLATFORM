package com.elp.dao;

import java.util.List;

import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Student;

public interface StudentDao {
	public String createStudent(Student student);
	public List<Course> viewEnrolledCourse(int courseId);
	public String addToCart(Cart cart);
	public List<Course> viewCart(int userId);
	public List<Course> searchCourses(String courseName);
	public String enroll(int userId,int courseId);
	public String unEnroll(int enrollId);
	public String updateStudent(Student student);
	public String deleteStudent(int userId);
	public Student getStudentById(int userId);
	public Student getStudentByUsername(String userName);
}
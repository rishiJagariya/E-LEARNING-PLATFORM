package com.elp.dao;

import java.util.List;

import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Student;

public interface StudentDao {
	public String createStudent(Student student);
	public List<Course> viewEnrolledCourse(int cid);
	public String addToCart(Cart cart);
	public List<Course> viewCart(int id);
	public List<Course> searchCourses(int cid);
	public String enroll(int uid,int cid);
	public String unEnroll(int eid);
	public String updateStudent(Student student);
	public String deleteStudent(int uid);
	public Student getStudentById(int uid);
	public Student getStudentByUsername(String uname);
}
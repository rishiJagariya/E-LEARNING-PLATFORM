package com.elp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elp.dao.StudentDao;
import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentDao studentDao;
	
	public StudentServiceImpl() { super(); }
	
	@Override
	public String createStudent(Student student) {
	 return studentDao.createStudent(student);
	}
	
	@Override
	public List<Course> viewEnrolledCourse(int courseId) {
		return studentDao.viewEnrolledCourse(courseId);
	}
	
	@Override
	public String addToCart(Cart cart) {
		return studentDao.addToCart(cart);
	}
	
	@Override
	public List<Course> viewCart(int userId) {
		return studentDao.viewCart(userId);
	}
	
	@Override
	public List<Course> searchCourses(String courseName) {
		return studentDao.searchCourses(courseName);
	}
	
	@Override
	public String enroll(int userId, int courseId) {
		return studentDao.enroll(userId, courseId);
	}
	
	@Override
	public String unEnroll(int enrollId) {
		return studentDao.unEnroll(enrollId);
	}
	
	@Override
	public String updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}
	
	@Override
	public String deleteStudent(Student student) {
		return studentDao.deleteStudent(student);
	}
	
	@Override
	public List<Student> getStudentById(int userId) {
		return studentDao.getStudentById(userId);
	}
	
	@Override
	public List<Student> getStudentByUsername(String username) {
		return studentDao.getStudentByUsername(username);
	}
}

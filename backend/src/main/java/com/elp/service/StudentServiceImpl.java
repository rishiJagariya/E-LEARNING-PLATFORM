package com.elp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elp.dao.StudentDao;
import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Student;

/**
 * @author SAGI YASWANTH
 *
 */
@Service("studentService")
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
	public String updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}
	
	@Override
	public String deleteStudent(int userId) {
		return studentDao.deleteStudent(userId);
	}
	
	@Override
	public Student getStudentById(int userId) {
		return studentDao.getStudentById(userId);
	}
	
	@Override
	public Student getStudentByUsername(String username) {
		return studentDao.getStudentByUsername(username);
	}

	@Override
	public String updatePassword(String username, String password) {
		return studentDao.updatePassword(username, password);
	}
	
	@Override
	public String enroll(int userId) {
		return studentDao.enroll(userId);
	}
	
	@Override
	public String unEnroll(int userId,int courseId) {
		return studentDao.unEnroll(userId,courseId);
	}
	
	@Override
	public String addToCart(int courseId,int userId) {
		return studentDao.addToCart(courseId,userId);
	}
	
	@Override
	public String removeFromCart(int courseId,int studentId) {
		return studentDao.removeFromCart(courseId,studentId);
	}
	
	@Override
	public List<Course> viewCart(int userId) {
		return studentDao.viewCart(userId);
	}

	@Override
	public List<Course> getCourseList() {
		return studentDao.getCourseList();
	}

	@Override
	public List<Course> getEnrolledCourseList(int userId) {
		return studentDao.getEnrolledCourseList(userId);
	}
	
	@Override
	public List<Course> searchCourses(String courseName) {
		return studentDao.searchCourses(courseName);
	}
}

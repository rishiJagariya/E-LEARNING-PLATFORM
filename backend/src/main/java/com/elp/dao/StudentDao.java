package com.elp.dao;

public interface StudentDao {
	public String createStudent(Student student);
	public List<Course> viewEnrolledCourse(int userId);
	public String addToCart(Course course);
	public List<Course> viewCart(int userId);
	public List<course> searchCourses(String courseName);
	public String enroll(int userId,int courseId);
	public String unEnroll(int enrollId);
	public String updateStudent(Student student);
	public String deleteStudent(Student student);
	public List<Student> getStudentById(int userId);
	public List<Student> getStudentByUsername(String username);
}
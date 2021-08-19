package com.elp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Student;
import com.mph.entity.Employee;

public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}	
	
	@Override
	public String createStudent(Student student) {
		getSession().saveOrUpdate(student);
		return "user created successfully";
		
	}

	@Override
	public List<Course> viewEnrolledCourse(int userId) {
		Query q = getSession().createQuery("");
		List<Course> emplist = q.list();
		return emplist; 
	}

	@Override
	public String addToCart(Cart cart) {
		getSession().save(cart);
		return "Added successfully";
	}

	@Override
	public List<Course> viewCart(int userId) {
		Query q = getSession().createQuery("from Cart");
		List<Course> emplist = q.list();
		return emplist; 
	}

	@Override
	public List<Course> searchCourses(String courseId) {
		Query q = getSession().createQuery("select courseName from Course where courseId=:courseId");
		List<Course> emplist = q.list();
		return emplist;
	}

	@Override
	public String enroll(int userId, int courseId) {
		getSession().createQuery("insert into Enrollment(userId,courseId)"+));
		return null;
	}

	@Override
	public String unEnroll(int enrollId) {
		getSession().createQuery("Delete from Enrollment where enrollId=:enrollId");
		return "Unenrolled";
	}

	@Override
	public String updateStudent(Student student) {
		getSession().saveOrUpdate(student);
		return "User updated successfully";
	}

	@Override
	public String deleteStudent(int userId) {
		Query q = getSession().createQuery("Delete from Student where userId=:userId");
		return "Deleted ;
	}

	@Override
	public Student getStudentById(int userId) {
		Query q = getSession().createQuery("from Student where userId=:userId");
		return null;
	}

	@Override
	public Student getStudentByUsername(String username) {
		Query q = getSession().createQuery("from Student where username=:username");
		Student st = (Student)q.uniqueResult();
		return st;
	}

	
}

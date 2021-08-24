package com.elp.dao;

import java.util.List;
import java.util.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import com.elp.entity.Cart;
import com.elp.entity.Course;
import com.elp.entity.Enrollment;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
	Date mydate = new Date();
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() 
	{
		return sessionFactory.getCurrentSession();
	}	
	
	@Override
	public String createStudent(Student student) {
		System.out.println("Into student Dao");
		String username = student.getUsername();
		try {
			Query<?> query = getSession().createQuery("select 1 from Student st where st.username= :username");
			query.setParameter("username", student.getUsername());
			if(query.uniqueResult()!=null) {
				return "User already exists";
			}
			else
			{
				getSession().save(student);
				return "user created successfully";
			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			return "Execption occured";
		}
	}
	
	@Override
	public String updateStudent(Student student) {
		Query query = getSession().createQuery("Update Student student set userName=:userName,password=:password,fname=:fname,lname=:lname,Dob=:Dob,phoneNo=:phoneNo,userType=:userType,enroll=:enroll where userId=:userId");
		query.setParameter("userName", student.getUsername());
		query.setParameter("password", student.getPassword());
		query.setParameter("fname", student.getFname());
		query.setParameter("lname", student.getLname());
		query.setParameter("Dob", student.getDob());
		query.setParameter("phoneNo", student.getPhoneNo());
		query.setParameter("userType", student.getUserType());
		query.setParameter("enroll", student.getEnroll());
		query.setParameter("trainerId", student.getUserId());
		query.executeUpdate();
		return "Updated Successfully";
	}

	@Override
	public String deleteStudent(int userId) {
		Query query = getSession().createQuery("Delete from Student where userId=:userId");
		query.setParameter("userId", userId);
		query.executeUpdate();
		return "Deleted" ;
	}
	
	@Override
	public Student getStudentById(int userId) {
		Query<Student> query = getSession().getNamedQuery("getStudentById");
		query.setParameter("userId",userId);
		Student student = query.uniqueResult();
		return student;
	}

	@Override
	public Student getStudentByUsername(String username) {
		Query<Student> query = getSession().getNamedQuery("getStudentByName");
		query.setParameter("userName",username);
		Student student = query.uniqueResult();
		return student;
	}
	
	@Override
	public String updatePassword(String username, String password) {
		Query query = getSession().createQuery("Update Student student set password=:password where username=:username");
		query.setParameter("username", username);
		query.setParameter("password",password);
		query.executeUpdate();
		return "Password Updated";
		
	}
	
	@Override
	public String enroll(int userId, int courseId) {
		Enrollment enrollment = new Enrollment();
		enrollment.setStudentId(userId);
		enrollment.setCourseId(courseId);
		enrollment.setDateOfEnroll(mydate.toString());//TODO: add property
		enrollment.setDateOfCompletion(null);
		getSession().save(enrollment);
		//System.out.println(courseId);
		int userid = enrollment.getStudentId();
		Query query = getSession().createQuery("from Student where userId=:userid");
		query.setParameter("userid", userid);
		Student student = (Student) query.uniqueResult();
		List<Integer> enrollList = student.getEnroll();
		System.out.print(enrollList);
		enrollList.add(userid);
		student.setEnroll(enrollList);
		getSession().update(student);
		System.out.println(student);
		return "enrolled";
	}

	@Override
	public String unEnroll(int enrollId) {
		Query query = getSession().createQuery("Delete from Enrollment where courseId=:enrollId");
		query.setParameter("enrollId", enrollId);
		return "Unenrolled";
	}
	
	@Override
	public String addToCart(Cart cart) {
		getSession().save(cart);
		return "Added successfully";
	}
	
	@Override
	public String removeFromCart(int courseId, int studentId) {
		Query query = getSession().createQuery("from Cart where userId=:studentId");
		query.setParameter("studentId",studentId);
		Cart cart = (Cart) query.uniqueResult();
		cart.getItems().remove(new Integer(courseId));
		getSession().update(cart);
		System.out.println(cart);
		return "Removed successfully";
	}
	
	@Override
	public List<Course> viewCart(int userId) {
		//Query query = getSession().createQuery("from Student where userId=:userId");
		//query.setParameter("userId", userId);
		//Student student = (Student) query.uniqueResult();
		//Cart cart = new Cart();
		//cart.setItems();
		//getSession().update(cart);
		Query query = getSession().createQuery("from Cart where userId=:userId");
		query.setParameter("userId", userId);
		Cart cart = (Cart) query.uniqueResult();
		List<Course> clist = null;
		for(int i : cart.getItems()) {
			Query query1 = getSession().createQuery("from Course where courseId=:i");
			query1.setParameter("i",i);
			Course course = (Course) query1.uniqueResult();
			clist.add(course);
		}
		return clist; 
	}
	
	@Override
	public List<Course> getCourseList(Course course) {
		Query query = getSession().createQuery("from Course");
		List<Course> CourseList = query.list();
		return CourseList;
	}

	
	
	@Override
	public List<Course> getEnrolledCourseList(int courseId) {
		//Query query = getSession().createQuery("from Course where courseId=:courseId");
		//query.setParameter("courseId", courseId);
		//Course course = 
		Query query = getSession().createQuery("select courseId from Student where" + courseId + "IN (:enroll)");
		List<Student> CourseList = query.list();
		Query query1 = getSession().createQuery("from Course where courseId" + CourseList);
		List<Course> course = query1.list();
		return course;
	}
	
	@Override
	public List<Course> searchCourses(String courseName) {
		Criteria c = getSession().createCriteria(Course.class);
		c.add(Restrictions.like("courseName","courseName%"));
		List<Course> course = c.list();
		return course;
	}
}

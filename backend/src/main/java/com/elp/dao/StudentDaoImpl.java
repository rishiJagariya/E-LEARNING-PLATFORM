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

/**
 * @author Rudresh Sunagad
 *
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
	Date mydate = new Date();
	@Autowired 
	private Cart cart;
	
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
		Query query = getSession().createQuery("Update Student student set userName=:userName,password=:password,fname=:fname,lname=:lname,Dob=:Dob,phoneNo=:phoneNo,userType=:userType where userId=:userId");
		query.setParameter("userName", student.getUsername());
		query.setParameter("password", student.getPassword());
		query.setParameter("fname", student.getFname());
		query.setParameter("lname", student.getLname());
		query.setParameter("Dob", student.getDob());
		query.setParameter("phoneNo", student.getPhoneNo());
		query.setParameter("userType", student.getUserType());
		query.setParameter("userId", student.getUserId());
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
	public String enroll(int userId) {
		Query query1 = getSession().createQuery("from Cart where userId=:userId");
		query1.setParameter("userId", userId);
		Cart cart = (Cart) query1.uniqueResult();
		List<Integer> items = cart.getItems();
		
		Query query = getSession().createQuery("from Student where userId=:userid");
		query.setParameter("userid", userId);
		Student student = (Student) query.uniqueResult();
		List<Integer> enrollList = student.getEnroll();
		System.out.print(enrollList);
		for(int i : items)
			enrollList.add(i);
		student.setEnroll(enrollList);
		getSession().update(student);
		
		cart.setItems(null);
//		Query query2 = getSession().createQuery("Delete from Cart where userId=:userId");
//		query2.setParameter("userId", userId);
//		query2.executeUpdate();
		getSession().update(cart);
		
		return "Success";
	}

	@Override
	public String unEnroll(int userId,int courseId) {
		Query query = getSession().createQuery("from Student where userId=:userId");
		query.setParameter("userId", userId);
		Student student = (Student) query.uniqueResult();
		List<Integer> cList = student.getEnroll();
		System.out.println(cList);
		for(int i : cList) {
			if(cList.contains(courseId))
			{
				Query query1 = getSession().createQuery("Delete from Enrollment where courseId=:courseid");
				query1.setParameter("courseid", courseId);
				query1.executeUpdate();
			}
		}
		cList.remove(userId);
		System.out.println(cList);
		student.setEnroll(cList);
		getSession().update(student);
		return "Unenrolled";
	}
	
	@Override
	//courseId,userId
	public String addToCart(int courseId,int userId) {
		Query query = getSession().createQuery("from Cart where userId=:userId");
		query.setParameter("userId", userId);
		Cart cart = (Cart) query.setMaxResults(1).uniqueResult();
		String message = "";
		if(cart == null){
			List<Integer> items = new ArrayList<Integer>();
			items.add(courseId);
			Cart newCart = new Cart();
			newCart.setUserId(userId);
			newCart.setItems(items);
			newCart.setDiscount(0);
			newCart.setTotalAmount(0);
			int total = findSum(newCart.getTotalAmount(), courseId);
			newCart.setTotalAmount(total);
			getSession().save(newCart);
			message = "Course added to Cart";	
		} else if(cart.getItems().contains(courseId)) {
			return "alreadt present into cart";
		} else {
			List<Integer> courseList = cart.getItems();
			courseList.add(courseId);
			cart.setItems(courseList);
			int total = findSum(cart.getTotalAmount(), courseId);
			cart.setTotalAmount(total);
			getSession().save(cart);
			message = "Course added to Cart";
		}	
		return message;
	}
	
	private int findSum(int totalSum, int courseId) {
		System.out.println(courseId);
		Query query = getSession().createQuery("from Course where courseId=:courseId");
		query.setParameter("courseId", courseId);
		Course course = (Course) query.uniqueResult();
		int courseFee = course.getFee();
		return courseFee + totalSum;
	}

	@Override
	public String removeFromCart(int courseId, int studentId) {
		Query query = getSession().createQuery("from Cart where userId=:studentId");
		query.setParameter("studentId",studentId);
		Cart cart = (Cart) query.setMaxResults(1).uniqueResult();
		List<Integer> cList = cart.getItems();
		cList.remove(new Integer(courseId));
		if(cList.contains(courseId))
		{
			System.out.println("Not deleted");
		}
		else
		{
			System.out.println("Deleted");
		}
		cart.setItems(cList);
		System.out.println(cList);
		getSession().update(cart);
		System.out.println(cart);
		Query query1 = getSession().createQuery("Delete from Course where courseId=:courseId");
		query1.setParameter("courseId", courseId);
		query1.executeUpdate();
		return "Success";
	}
	
	@Override
	public List<Course> viewCart(int userId) {
		Query query = getSession().createQuery("from Cart where userId=:userId");
		query.setParameter("userId", userId);
		Cart cart = (Cart) query.setMaxResults(1).uniqueResult();
		List<Course> clist = new ArrayList<Course>();
		List<Integer> listOfCourseId = cart.getItems();
		System.out.println(listOfCourseId);
		for(int courseId : listOfCourseId) {
			Query query1 = getSession().createQuery("from Course where courseId=:courseId");
			query1.setParameter("courseId",courseId);
			Course course = (Course) query1.setMaxResults(1).uniqueResult();
			clist.add(course);
		}
		return clist; 
	}
	
	@Override
	public List<Course> getCourseList() {
		Query query = getSession().createQuery("from Course");
		List<Course> CourseList = query.list();
		return CourseList;
	}
	
	@Override
	public List<Course> getEnrolledCourseList(int userId) {
		System.out.println("I'm here into studentDao");
		Query query = getSession().createQuery("from Student where userId=:userId");
		query.setParameter("userId",userId);
		Student student = (Student) query.uniqueResult();
		List<Integer> enrollList = student.getEnroll();
		List<Course> courseList = new ArrayList<Course>();
		for(int courseId : enrollList) {
			Query query2 = getSession().createQuery("from Course where courseId=:courseId");
			query2.setParameter("courseId", courseId);
			Course course = (Course) query2.setMaxResults(1).uniqueResult();
			courseList.add(course);
		}
		
		return courseList;
	}
	
	@Override
	public List<Course> searchCourses(String courseName) {
		Query query = getSession().createQuery("from Course where courseName=:courseName");
		query.setParameter("courseName", courseName);
		List<Course> course = query.list();
		return course;
		/*Criteria c = getSession().createCriteria(Course.class);
		c.add(Restrictions.eq("courseName","courseName"));
		List<Course> course = c.list();
		return course;*/
	}
}

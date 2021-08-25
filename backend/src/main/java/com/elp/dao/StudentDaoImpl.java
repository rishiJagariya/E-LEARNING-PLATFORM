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
	public String unEnroll(int userId,int courseId) {
		Query query = getSession().createQuery("from Student where userId=:userId");
		query.setParameter("userId", userId);
		Student student = (Student) query.uniqueResult();
		List<Integer> cList = student.getEnroll();
		if(cList.contains(courseId))
		{
			student.getEnroll().remove(courseId);
			Query query1 = getSession().createQuery("Delete from Enrollment where courseId=:courseId");
			query1.setParameter("courseId", courseId);
			query.executeUpdate();
		}
		return "Unenrolled";
	}
	
	@Override
	//courseId,userId
	public String addToCart(int courseId,int userId) {
		
		/*switch(expression)
		{
		case 1:
				Query query = getSession().createQuery("from Cart where userId=:userId");
				Cart cart = (Cart) query.setMaxResults(1).uniqueResult();
				List<Integer> courseList = cart.getItems();
				if(courseList.contains(courseId))
				{
					System.out.println("Course present in Cart");
				else {
					courseList.add(courseId);
				}
		
			case 2://student not present in cart
				List<Integer> cList = null;
				cList.add(courseId);
				Cart cart1 = new Cart();
				cart1.setUserId(userId);
				cart1.setItems(cList);
				getSession().save(cart1);
		}*/
		Query query = getSession().createQuery("from Cart where userId=:userId");
		query.setParameter("userId", userId);
		Cart cart = (Cart) query.setMaxResults(1).uniqueResult();
		List<Integer> courseList = cart.getItems();
		if(courseList == null)
		{
			courseList.add(courseId);
		}
		else if(courseList.contains(courseId))
		{
			System.out.println("Course present in Cart");
		}
		else {
			courseList.add(courseId);
			cart.setItems(courseList);
			getSession().save(cart);
			System.out.println("Course added to Cart");
		}	
		System.out.println(courseList);
		cart.setItems(courseList);
		getSession().save(cart);
		return "Course added to Cart";
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
		Cart cart = (Cart) query.setMaxResults(1).uniqueResult();
		List<Course> clist = null;
		List<Integer> cList = cart.getItems();
		System.out.println(cList);
		for(int i : cList) {
			Query query1 = getSession().createQuery("from Course where courseId=:i");
			query1.setParameter("i",i);
			Course course = (Course) query1.setMaxResults(1).uniqueResult();
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
	public List<Course> getEnrolledCourseList(int userId) {
		Query query = getSession().createQuery("from Student where userId=:userId");
		query.setParameter("userId",userId);
		Student student = (Student) query.uniqueResult();
		List<Course> courseList = null;
		for(int courseId : student.getEnroll()) {
			Query query2 = getSession().createQuery("from Coursetable where courseId=:courseId");
			query.setParameter("courseId", courseId);
			Course course = (Course) query2.setMaxResults(1).uniqueResult();
			courseList.add(course);
		}
		
		return courseList;
	}
	
	@Override
	public List<Course> searchCourses(String courseName) {
		Criteria c = getSession().createCriteria(Course.class);
		c.add(Restrictions.like("courseName","courseName%"));
		List<Course> course = c.list();
		return course;
	}
}

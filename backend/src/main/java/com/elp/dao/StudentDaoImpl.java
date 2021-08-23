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
				getSession().saveOrUpdate(student);
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
		return "Updated Successfully";
	}

	@Override
	public String deleteStudent(int userId) {
		Query query = getSession().createQuery("Delete from Student where userId=:userId");
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
		Query query = getSession().createQuery("Update Student student set username=:username,password=:password where username=:username");
		query.setParameter("username", username);
		query.setParameter("password",password);
		return "Password Updated";
		
	}
	
	@Override
	public String enroll(int studentId, int courseId) {
		Enrollment enrollment = new Enrollment();
		enrollment.setStudentId(studentId);
		enrollment.setCourseId(courseId);
		enrollment.setDateOfEnroll(mydate.toString());//TODO: add property
		enrollment.setDateOfCompletion(null);
		getSession().saveOrUpdate(enrollment);
		return null;
	}

	@Override
	public String unEnroll(int enrollId) {
		getSession().createQuery("Delete from Enrollment where enrollId=:enrollId");
		return "Unenrolled";
	}
	
	@Override
	public String addToCart(Cart cart) {
		getSession().save(cart);
		return "Added successfully";
	}
	
	@Override
	public String removeFromCart(int courseId) {
		Query query = getSession().createQuery("Delete from Cart where courseId IN (:items)");
		//Query query = getSession().createQuery("select userId from Cart where )"
		return "Removed successfully";
	}
	
	@Override
	public List<Course> viewCart(int userId) {
		Query query = getSession().createQuery("from Cart where userId=:userId");
		List<Course> clist = query.list();
		return clist; 
	}
	
	@Override
	public List<Course> getCourseList(Course course) {
		Query query = getSession().createQuery("from Course");
		return null;
	}

	
	
	@Override
	public List<Course> getEnrolledCourseList(int courseId) {
		Query query = getSession().createQuery("select Course.courseName from Course INNERJOIN Enrollment ON Enrollment.courseId=:courseId;");
		List<Course> stlist = query.list();
		return stlist; 
	}
	
	@Override
	public List<Course> searchCourses(String courseName) {
		Criteria c = getSession().createCriteria(Course.class);
		c.add(Restrictions.like("courseName","courseName%"));
		List<Course> course = c.list();
		return course;
	}
}
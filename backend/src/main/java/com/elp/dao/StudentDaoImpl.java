package com.elp.dao;

import java.util.List;

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
	public List<Course> viewEnrolledCourse(int courseId) {
		Query query = getSession().createQuery("select Course.courseName,Course.courseId from Course INNERJOIN Enrollment ON Course.courseId=Enrollment.cid;");
		List<Course> emplist = query.list();
		return emplist; 
	}

	@Override
	public String addToCart(Cart cart) {
		getSession().save(cart);
		return "Added successfully";
	}

	@Override
	public List<Course> viewCart(int userId) {
		Query q = getSession().createQuery("from Cart where userId=:userId");
		List<Course> clist = q.list();
		return clist; 
	}

	@Override
	public List<Course> searchCourses(String courseName) {
		Query query = getSession().createQuery("select courseName from Course where courseName LIKE '%:courseName%");
		List<Course> clist = query.list();
		return clist;
	}

	@Override
	public String enroll(int studentId, int courseId) {
		Enrollment enrollment = new Enrollment();
		enrollment.setStudentId(studentId);
		enrollment.setCourseId(courseId);
		enrollment.setDateOfEnroll(null);//TODO: add property
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
	public String updateStudent(Student student) {
		getSession().saveOrUpdate(student);
		return "User updated successfully";
	}

	@Override
	public String deleteStudent(int userId) {
		Query query = getSession().createQuery("Delete from Student where userId=:userId");
		return "Deleted" ;
	}

	@Override
	public Student getStudentById(int userId) {
		Query query = getSession().createQuery("from Student where userId=:userId");
		Student student = (Student)query.uniqueResult();
		return student;
	}

	@Override
	public Student getStudentByUsername(String username) {
		Criteria c = getSession().createCriteria(Student.class);
		c.add(Restrictions.eq("username",username));
		Student student = (Student)c.uniqueResult();
		return student;
	}
}
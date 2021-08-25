package com.elp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

@Repository("trainerDao")
public class TrainerDaoImpl implements TrainerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}	
	@Override
	public String createTrainer(Trainer trainer) {
		System.out.println("Into trainer Dao");
		String username = trainer.getUsername();
		try {
			Query query = getSession().createQuery("select 1 from Trainer t where t.username= :username");
			query.setParameter("username", trainer.getUsername());
			if(query.uniqueResult()!=null) {
				return "User already exists";
			}
			else
			{
				System.out.println("Into else part");
				getSession().save(trainer);
				System.out.println("after save and update");
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
	public String updateTrainer(Trainer trainer) {
		Query query = getSession().createQuery("Update Trainer trainer set userName=:userName,password=:password,fname=:fname,lname=:lname,Dob=:Dob,phoneNo=:phoneNo,userType=:userType,courseOffered=:courseOffered where userId=:trainerId");
		query.setParameter("userName", trainer.getUsername());
		query.setParameter("password", trainer.getPassword());
		query.setParameter("fname", trainer.getFname());
		query.setParameter("lname", trainer.getLname());
		query.setParameter("Dob", trainer.getDob());
		query.setParameter("phoneNo", trainer.getPhoneNo());
		query.setParameter("userType", trainer.getUserType());
		List<Integer> courseList = trainer.getCourseOffered();
		query.setParameter("courseOffered", courseList);
		query.setParameter("trainerId", trainer.getUserId());
		query.executeUpdate();
		return "Updated Successfully";
	}
	
	@Override
	public String deleteTrainer(int userId) {
		Query query = getSession().createQuery("Delete from Trainer where userId=:userId");
		query.setParameter("userId", userId);
		query.executeUpdate();
		return "Deleted" ;
	}
	
	@Override
	public Trainer getTrainerById(int userId) {
		Query<Trainer> query = getSession().getNamedQuery("getTrainerById");
		query.setParameter("userId",userId);
		Trainer trainer = query.uniqueResult();
		return trainer;
	}

	@Override
	public Trainer getTrainerByUsername(String username) {
		Query<Trainer> query = getSession().getNamedQuery("getTrainerByName");
		query.setParameter("userName", username);
		Trainer trainer = query.uniqueResult();
		return trainer;
	}
	
	@Override
	public String updatePassword(String username, String password) {
		Query query = getSession().createQuery("Update Trainer trainer set password=:password where username=:username");
		query.setParameter("username", username);
		query.setParameter("password",password);
		query.executeUpdate();
		return "Password Updated";
	}
	
	@Override
	public String createCourse(Course course) {
		getSession().save(course);
		System.out.println(course);
		int courseId = course.getCourseId();
		System.out.println(courseId);
		Query<?> query = getSession().createQuery("from Trainer where userId=:trainerId");
		query.setParameter("trainerId", course.getTrainerId());
		Trainer trainer = (Trainer) query.uniqueResult();
		System.out.print(trainer.getFname());
		List<Integer> courseList = trainer.getCourseOffered();
		System.out.print(courseList);
		courseList.add(courseId);
		trainer.setCourseOffered(courseList);
		getSession().update(trainer);
		System.out.println(trainer);
		return "Success";
	}

	@Override
	public String updateCourse(String username, Course course) {
		System.out.println("dao layer");
		Query query1 = getSession().createQuery("select userId from Trainer where username=:username");
		query1.setParameter("username", username);
		int userid = (int) query1.uniqueResult();
		System.out.println(userid);
		if(userid==course.getTrainerId())
		{
			System.out.println("3");
			Query query = getSession().createQuery("Update Course c set courseName=:courseName,fee=:fee,duration=:duration,rating=:rating,description=:description,category=:category where trainerId=:userid");
			query.setParameter("userid",userid);
			System.out.println("4");
			query.setParameter("courseName",course.getCourseName());
			query.setParameter("fee",course.getFee());
			query.setParameter("duration",course.getDuration());
			query.setParameter("rating",course.getRating());
			query.setParameter("description",course.getDescription());
			query.setParameter("category",course.getCategory());
			int noofrows = query.executeUpdate();
			if(noofrows >0)
			{
				System.out.println("Updated " + noofrows + " rows");
			}
			return "Updated Successfully";
		}
		else
		{
			return "You cannot Update this Course";
		}
	}
	
	@Override
	public String deleteCourse(String username,int courseId) {
		Query query = getSession().createQuery("select userId from Trainer where username=:username");
		query.setParameter("username",username);
		int userid = (int) query.uniqueResult();
		Query query1 = getSession().createQuery("from Course where courseId=:courseId");
		query1.setParameter("courseId",courseId);
		Course course = (Course) query1.uniqueResult();
		if(userid==course.getTrainerId())
		{
			Query query2 = getSession().createQuery("Delete from Course where courseId=:courseId");
			query2.setParameter("courseId", courseId);
			query2.executeUpdate();
		}
		return "Deleted";
	}
	
	@Override
	public List<Course> getTrainerCourseList(String username) {
		System.out.println(username);		
		Query<?> query = getSession().createQuery("select userId from Trainer where username=:username");
		query.setParameter("username",username);
		Integer userid = (Integer) query.uniqueResult();
		System.out.println(userid);
		Query query1 = getSession().createQuery("select courseName from Course where trainerId=:userid");
		query1.setParameter("userid", userid);
		List<Course> course = query1.list();
		return course;
	}

	@Override
	public List<Student> getStudentEnrollList(int courseId) {
		Query query = getSession().createQuery("select studentId from Enrollment where courseId=:courseId");
		query.setParameter("courseId", courseId);
		List<Student> userid = query.list();
		Query query1 = getSession().createQuery("select userName from Student where userid IN (:enroll)");
		query.setParameter("userid", userid);
		List<Student> student = query1.list();
		return student;
	}

	@Override//changing String courseName
	//, String courseName
	public List<Course> searchTrainerCourses(String username) {
		Query query = getSession().createQuery("select userId from Trainer where username=:username");
		query.setParameter("username", username);
		int userid = (int) query.uniqueResult();
		Query query1 = getSession().createQuery("select courseName from Course where trainerId=:userid");
		query.setParameter("userid", userid);
		List<Course> course = query1.list();
		return course;
	}
}

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
			Query<?> query = getSession().createQuery("select 1 from Trainer t where t.username= :username");
			query.setParameter("username", trainer.getUsername());
			if(query.uniqueResult()!=null) {
				return "User already exists";
			}
			else
			{
				System.out.println("Into else part");
				getSession().saveOrUpdate(trainer);
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
	public String createCourse(Course course) {
		getSession().saveOrUpdate(course);
		return "Course created";
	}

	@Override
	public String updateCourse(String userName,Course course) {
		Query query = getSession().createQuery("Update Course course set courseName=:courseName,fee=:fee,duration=:duration,rating=:rating,trainerId=:trainerId,description=:description,category=:category where courseId=:courseId");
		query.setParameter("courseName",course.getCourseName());
		query.setParameter("fee",course.getFee());
		query.setParameter("duration",course.getDuration());
		query.setParameter("rating",course.getRating());
		query.setParameter("trainerId",course.gettrainerId());
		query.setParameter("description",course.getDescription());
		query.setParameter("category",course.getCategory());
		return "Updated Successfully";
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
		query.setParameter("courseOffered", trainer.getCourseOffered());
		query.setParameter("trainerId", trainer.getUserId());
		return "Updated Successfully";
	}

	@Override
	public List<Course> getTrainerCourseList(String userName) {
		Query query = getSession().createQuery("from Course INNERJOIN Trainer ON Course.trainerId=Trainer.userId");
		List<Course> course = query.list();
		return course;
	}

	@Override
	public List<Student> getStudentEnrollList(int courseId) {
		Query query = getSession().createQuery("select Enrollment.userId from Enrollment where courseId=:courseId");
		List<Student> userid = query.list();
		Query query1 = getSession().createQuery("select userName from Student where userId=:userid");
		List<Student> student = query1.list();
		return student;
	}


	@Override
	public String deleteCourse(String username,int courseId) {
		Query query = getSession().createQuery("Delete from Trainer where courseId IN (:courseOffered) and username=:username");
		return "Course deleted";
	}
	@Override
	public String updatePassword(String username, String password) {
		Query query = getSession().createQuery("Update Trainer trainer set username=:username,password=:password where username=:username");
		query.setParameter("username", username);
		query.setParameter("password",password);
		return "Password Updated";
	}
	@Override
	public String deleteTrainer(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Course> searchTrainerCourses(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}
}

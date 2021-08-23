package com.elp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		return null;
	}

	//TODO: complete it Rudresh
	@Override
	public Trainer getTrainerByUsername(String username) {
		Query query = getSession().createQuery("from Trainer where username=:username");
		Trainer trainer = (Trainer)query.uniqueResult();
		return trainer;
	}

	@Override
	public String createCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateTrainer(Trainer trainer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> viewTrainerCourse(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> listOfStudentsEnrolled(int courseid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String deleteCourse(String username, int courseId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateCourse(String username, Course course) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
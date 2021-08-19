package com.elp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;
import com.elp.entity.User;

public class TrainerDaoImpl implements TrainerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}	
	@Override
	public String createTrainer(Trainer trainer) {
		try {
			Query query = getSession().createQuery("select username from Trainer where username="+trainer.getUsername());
			if(query!=null) {
				return "User already exists";
			}
			else
			{
				getSession().saveOrUpdate(trainer);
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

	@Override
	public Trainer getTrainerByUsername(String username) {
		
		return null;
	}

	@Override
	public String createCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCourse(Course course) {
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
	public String deleteCourse(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
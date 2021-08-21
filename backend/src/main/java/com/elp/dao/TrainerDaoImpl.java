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
	public String updateCourse(Course course) {
		return "Updated Successfully";
	}

	@Override
	public String updateTrainer(Trainer trainer) {
		return "Updated Successfully";
	}

	@Override
	public List<Course> getTrainerCourse(int trainerId) {
		Query query = getSession().createQuery("from Course INNERJOIN Trainer ON Course.userId=Trainer.userId");
		List<Course> 
	}

	@Override
	public List<Student> listOfStudentsEnrolled(int courseId) {
		Query query = getSession().createQuery("select Enrollment.userId from Enrollment where courseId=:courseId");
		List<Integer> userid = query.list();
		Query query1 = getSession().createQuery("select userName from Student where userId=:userid");
		List<Student> student = query1.list();
		return student;
	}


	@Override
	public String deleteCourse(int courseId) {
		Query query = getSession().createQuery("Delete from Trainer where courseId IN (:courseOffered)");
		return "Course deleted";
	}
}

package com.elp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elp.dao.TrainerDao;
import com.elp.entity.Course;
import com.elp.entity.Trainer;
import com.elp.entity.User;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService{
	
	@Autowired
	TrainerDao trainerDao;
	
	public TrainerServiceImpl() { super(); }
	
	@Override
	public String createTrainer(Trainer trainer) {
		return trainerDao.createTrainer(trainer);
	}
	
	@Override
	public Trainer getTrainerById(int userId) {
		return trainerDao.getTrainerById(userId);
	}
	
	@Override
	public Trainer getTrainerByName(String username) {
		return trainerDao.getTrainerByName(username);
	}
	
	@Override
	public String createCourse(Course course) {
		return trainerDao.createCourse(course);
	}
	
	@Override
	public String updateCourse(Course course) {
		return trainerDao.updateCourse(course);
	}
	
	@Override
	public String updateTrainer(Trainer trainer) {
		return trainerDao.updateTrainer(trainer);
	}
	
	@Override
	public List<Course> viewTrainerCourse(int userId) {
		return trainerDao.viewTrainerCourse(userId);
	}
	
	@Override
	public List<User> listOfStudentsEnrolled(int courseid) {
		return trainerDao.listOfStudentsEnrolled(courseid);
	}
	
	@Override
	public String deleteCourse(int courseId) {
		return trainerDao.createCourse(null);
	}
	
}

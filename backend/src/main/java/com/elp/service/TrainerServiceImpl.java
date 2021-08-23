package com.elp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elp.dao.TrainerDao;
import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

@Service("trainerService")
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
	public String updateTrainer(Trainer trainer) {
		return trainerDao.updateTrainer(trainer);
	}
	
	@Override
	public String deleteTrainer(int userId) {
		return trainerDao.deleteTrainer(userId);
	}
	
	@Override
	public Trainer getTrainerById(int userId) {
		return trainerDao.getTrainerById(userId);
	}
	
	@Override
	public Trainer getTrainerByUsername(String username) {
		return trainerDao.getTrainerByUsername(username);
	}
	
	@Override
	public String updatePassword(String username, String password) {
		return trainerDao.updatePassword(username, password);
	}
	
	@Override
	public String createCourse(Course course) {
		return trainerDao.createCourse(course);
	}
	
	@Override
	public String updateCourse(String username, Course course) {
		return trainerDao.updateCourse(username, course);
	}

	@Override
	public String deleteCourse(String username, int courseId) {
		return trainerDao.deleteCourse(username, courseId);
	}

	@Override
	public List<Course> getTrainerCourseList(String username) {
		return trainerDao.getTrainerCourseList(username);
	}

	@Override
	public List<Student> getStudentEnrollList(int courseId) {
		return trainerDao.getStudentEnrollList(courseId);
	}

	@Override
	public List<Course> searchTrainerCourses(String username) {
		return trainerDao.searchTrainerCourses(username);
	}
	
}

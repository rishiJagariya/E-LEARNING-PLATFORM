package com.elp.dao;

import java.util.List;

import com.elp.entity.Course;
import com.elp.entity.Trainer;
import com.elp.entity.User;

public interface TrainerDao {
	public String createTrainer(Trainer trainer);
	public Trainer getTrainerById(int userId);
	public String createCourse(Course course);
	public String updateCourse(Course course);
	public String updateTrainer(Trainer trainer);
	public List<Course> viewTrainerCourse(int userId);
	public List<User> listOfStudentsEnrolled(int courseid);
	public String deleteCourse(int courseId);
	public Trainer getTrainerByUsername(String username);
}
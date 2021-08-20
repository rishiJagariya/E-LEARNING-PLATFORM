package com.elp.service;

import java.util.List;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

public interface TrainerService {
	
	public String createTrainer(Trainer trainer);
	public Trainer getTrainerById(int userId);
	public Trainer getTrainerByUsername(String username);
	public String createCourse(Course course);
	public String updateCourse(String username,Course course);
	public String updateTrainer(Trainer trainer);
	public List<Course> viewTrainerCourse(int userId);
	public List<Student> listOfStudentsEnrolled(int courseId);
	public String deleteCourse(String username,int courseId);
	public String updatePassword(String username, String password);
}

package com.elp.service;

import java.util.List;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

public interface TrainerService {
	
	public String createTrainer(Trainer trainer);
	public String updateTrainer(Trainer trainer);
	public String deleteTrainer(int userId);
	public Trainer getTrainerById(int userId);
	public Trainer getTrainerByUsername(String username);
	public String updatePassword(String username, String password);
	public String createCourse(Course course);
	public String updateCourse(String username,Course course);
	public String deleteCourse(String username,int courseId);
	public List<Course> getTrainerCourseList(String username);
	public List<Student> getStudentEnrollList(int courseId);
	public List<Course> searchTrainerCourses(String username, String courseName);
}

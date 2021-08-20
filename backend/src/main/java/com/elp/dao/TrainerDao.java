package com.elp.dao;

import java.util.List;

import com.elp.entity.Course;
import com.elp.entity.Student;
import com.elp.entity.Trainer;

public interface TrainerDao {
	public String createTrainer(Trainer trainer);
	public Trainer getTrainerById(int userId);
	public Trainer getTrainerByUsername(String username);
	public String createCourse(Course course);
	public String updateCourse(Course course);
	public String updateTrainer(Trainer trainer);
	public List<Course> getTrainerCourse(int userId);
	public List<Student> listOfStudentsEnrolled(int courseId);
	public String deleteCourse(int courseId);
}

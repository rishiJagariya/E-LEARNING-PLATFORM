package com.elp.dao;

public interface TrainerDao {
	public String createTrainer(Trainer trainer);
	public Trainer getTrainerById(int userId);
	public Trainer getTrainerByName(String username);
	public String createCourse(Course course);
	public String updateCourse(Course course);
	public String updateTrainer(Trainer trainer);
	public List<Course> viewTrainerCourse(int userId);
	public List<User> listOfStudentsEnrolled(int courseid);
	public String deleteCourse(int courseId);
}
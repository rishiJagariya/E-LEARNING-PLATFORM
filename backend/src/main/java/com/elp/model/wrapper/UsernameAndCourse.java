package com.elp.model.wrapper;

import com.elp.entity.Course;


/**
 * @author Rishi Jagariya
 *
 */
public class UsernameAndCourse {
	String username;
	Course course;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Course getCourse() {
		return course;
	}
	@Override
	public String toString() {
		return "UsernameAndCourse [username=" + username + ", course=" + course + "]";
	}
	public UsernameAndCourse(String username, Course course) {
		super();
		this.username = username;
		this.course = course;
	}
	public UsernameAndCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}

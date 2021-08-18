package com.elp.entity;



import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer")
public class Trainer extends User{
	
	 @ElementCollection(targetClass=Integer.class)
	private List<Integer> courseOffered;
	public Trainer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Trainer(User user) {
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFname(user.getFname());
		this.setLname(user.getLname());
		this.setDob(user.getDob());
		this.setPhoneNo(user.getPhoneNo());
		this.setUserId(user.getUserId());
		this.setUserType(user.getUserType());
		this.courseOffered = null;
	}

	public List<Integer> getCourseOffered() {
		return courseOffered;
	}

	public void setCourseOffered(List<Integer> courseOffered) {
		this.courseOffered = courseOffered;
	}

	public Trainer(int userId, String userType, String username, String password, String fname, String lname,
			String dob, String phoneNo) {
		super(userId, userType, username, password, fname, lname, dob, phoneNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Trainer [courseOffered=" + courseOffered + "]";
	}
	
	
}


package com.elp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
/**
 * @author Roli Rai
 *
 */
@NamedQueries(value = { @NamedQuery(name = "getTrainerById", query = "from Trainer where userId=:userId"),
		@NamedQuery(name = "getTrainerByName", query = "from Trainer where Username=:userName")})
@Entity
@Table(name = "trainer")
public class Trainer extends User{
	
	@ElementCollection(targetClass=Integer.class)
	//@ElementCollection(targetClass=Course.class)
	private List<Integer> courseOffered;
	 
	public Trainer() {
		super();
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
	}

	@Override
	public String toString() {
		return "Trainer [courseOffered=" + courseOffered + ", userId=" + userId + ", userType=" + userType
				+ ", username=" + username + ", password=" + password + ", fname=" + fname + ", lname=" + lname
				+ ", dob=" + dob + ", phoneNo=" + phoneNo + "]";
	}
}
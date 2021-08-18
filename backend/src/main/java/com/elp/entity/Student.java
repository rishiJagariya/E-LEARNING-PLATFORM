package com.elp.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {
	
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> enroll;
    
	
	public List<Integer> getEnroll() {
		return enroll;
	}

	public void setEnroll(List<Integer> enroll) {
		this.enroll = enroll;
	}

	public Student(User user) {
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFname(user.getFname());
		this.setLname(user.getLname());
		this.setDob(user.getDob());
		this.setPhoneNo(user.getPhoneNo());
		this.setUserId(user.getUserId());
		this.setUserType(user.getUserType());
		this.enroll = null;
	}

	@Override
	public String toString() {
		return "Student [enroll=" + enroll + "]";
	}

	public Student(List<Integer> enroll) {
		super();
		this.enroll = enroll;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}

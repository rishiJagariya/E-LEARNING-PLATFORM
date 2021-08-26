package com.elp.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
/**
 * @author Roli Rai
 *
 */
@NamedQueries(value = { @NamedQuery(name = "getStudentById", query = "from Student where userId=:userId"),
		@NamedQuery(name = "getStudentByName", query = "from Student where username=:userName")})
@Entity
@Table(name = "student")
public class Student extends User {
	
	@ElementCollection(targetClass=Integer.class, fetch = FetchType.EAGER)
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
		return "Student [enroll=" + enroll + ", userId=" + userId + ", userType=" + userType + ", username=" + username
				+ ", password=" + password + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob + ", phoneNo="
				+ phoneNo + "]";
	}

	public Student(List<Integer> enroll) {
		super();
		this.enroll = enroll;
	}

	

	

	

}
package com.elp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Roli Rai
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="usertable")
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int userId;
	
	@Column
	protected String userType;
	
	@Column(unique=true)
	protected String username;
	
	@Column
	protected String password;
	
	@Column
	protected String fname;
	
	@Column
	protected String lname;
	
	@Column
	protected String dob;
	
	@Column
	protected String phoneNo;
	
	public User() {
		super();
	}
	
	public User(int userId, String userType, String username, String password, String fname, String lname,
			String dob, String phoneNo) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.phoneNo = phoneNo;
	}
	
	public User(Trainer user) {
		super();
		this.userId = user.userId;
		this.username = user.username;
		this.password = user.password;
		this.userType = user.userType;
		this.dob = user.dob;
		this.fname = user.fname;
		this.lname = user.lname;
		this.phoneNo = user.phoneNo;
	}
	
	public User(Student user) {
		super();
		this.userId = user.userId;
		this.username = user.username;
		this.password = user.password;
		this.userType = user.userType;
		this.dob = user.dob;
		this.fname = user.fname;
		this.lname = user.lname;
		this.phoneNo = user.phoneNo;
	}

	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userType=" + userType + ", username=" + username + ", password="
				+ password + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob + ", phoneNo=" + phoneNo
				+ "]";
	}	
}
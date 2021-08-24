package com.elp.model.wrapper;

public class LoginData {
	private String username;
	private String password;
	private String userType;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public LoginData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginData(String username, String password, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "LoginData [username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}

}

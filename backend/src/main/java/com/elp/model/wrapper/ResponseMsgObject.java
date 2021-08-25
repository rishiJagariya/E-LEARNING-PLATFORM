package com.elp.model.wrapper;

/**
 * @author Rishi Jagariya
 *
 */
public class ResponseMsgObject {
	private String message;
	private String userType;
	private String username;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	@Override
	public String toString() {
		return "ResponseMsgObject [message=" + message + ", userType=" + userType + ", username=" + username + "]";
	}
	public ResponseMsgObject(String message, String userType, String username) {
		super();
		this.message = message;
		this.userType = userType;
		this.username = username;
	}
	public ResponseMsgObject() {
		super();
		// TODO Auto-generated constructor stub
	}
}

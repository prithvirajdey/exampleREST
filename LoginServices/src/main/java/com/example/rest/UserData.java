package com.example.rest;

public class UserData {
	
	// Simple POJO
	String userId;
	String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", password=" + password + "]";
	}

}

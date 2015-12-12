package com.example.rest;

public class Message {
	
	// Simple message POJO
	String message;
	String error;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}

package com.example.rest;

import java.util.HashMap;

// Simple local class to store users in local memory for now
public final class UserMaintain {
	int i=0;
	public static HashMap<String, String> usersMap = new HashMap<String, String>();
	// Adding a pre-existing user.
	static{
	usersMap.put("admin","admin");
	}
}

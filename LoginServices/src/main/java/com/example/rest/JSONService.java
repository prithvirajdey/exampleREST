package com.example.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/services")
public class JSONService {

	@GET
	@Path("/echo/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response echoTest(@PathParam("param") String msg) {
		String output = "Echo : " + msg;
		return Response.status(200).entity(output).build();

	}

	// Service to Register new Users
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(UserData userData) {
		System.out.println("In user register");
		Message msg = new Message();
		if (userData == null) {
			System.out.println("Null input");
			msg.setError("Null Input");
			return Response.status(500).entity(msg).build();
		}
		if (userData.getUserId() == null
				|| userData.getUserId().trim().equals("")) {
			System.out.println("User ID cannot be null or blank");
			msg.setError("User ID cannot be null or blank");
			return Response.status(500).entity(msg).build();
		} else if (userData.getPassword() == null
				|| userData.getPassword().trim().equals("")) {
			System.out.println("Password cannot be null or blank");
			msg.setError("Password cannot be null or blank");
			return Response.status(500).entity(msg).build();
		}

		if (UserMaintain.usersMap.containsKey(userData.getUserId())) {
			System.out.println("User exists");
			msg.setMessage("User" + userData.getUserId() + "Already Exists");
			return Response.status(200).entity(msg).build();
		}
		try {
			System.out.println("Adding new user");
			UserMaintain.usersMap.put(userData.getUserId(),
					userData.getPassword());
		} catch (Exception e) {
			System.out.println("Error in adding User !");
			e.printStackTrace();
			msg.setError("Unexpected error occured");
			return Response.status(500).entity(msg).build();
		}
		msg.setMessage("Registration for " + userData.getUserId()
				+ " Successful");
		return Response.status(200).entity(msg).build();
	}

	// Service to authenticate Users
	@POST
	@Path("/auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authUser(UserData userData) {
		Message msg = new Message();
		if (userData == null) {
			System.out.println("Null inputs");
			msg.setError("Null Input");
			return Response.status(500).entity(msg).build();
		}

		if (!UserMaintain.usersMap.containsKey(userData.getUserId())) {
			System.out.println("User" + userData.getUserId()
					+ " does not exist");
			msg.setMessage("User" + userData.getUserId() + " Does not Exist");
			return Response.status(200).entity(msg).build();
		} else {
			if (userData.getPassword().equals(
					UserMaintain.usersMap.get(userData.getUserId()))) {
				// Set cookie for latest login time
				System.out.println("Setting cookie after auth");
				// Adding this as a backup in case CORS cookie is not read
				msg.setLastLogin(getDateString());
				NewCookie cookie = new NewCookie("last_access_t",
						msg.getLastLogin());
				System.out.println("User" + userData.getUserId()
						+ "authenticated");
				msg.setMessage("User Authenticated");
				return Response.status(200).entity(msg).cookie(cookie).build();
			}

		}
		System.out
				.println("User" + userData.getUserId() + " not authenticated");
		msg.setMessage("User Not Authenticated");
		return Response.status(200).entity(msg).build();

	}

	private String getDateString() {
		final String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(new Date());

		return utcTime;
	}

}
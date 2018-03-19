package edu.ycp.cs320.jminor.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import website4.model.usser;

public class userTest {
	
	private usser user1;
	private String username;
	private String password;
	private usser userGuest;
	String email = "email";
	
	@Before
	public void setup() throws Exception{
		username =  "usernameXD";
		password = "passwordXD";
		user1 = new usser(username, password);
	}
	
	@Test
	public void testGenericUser() { //tests real user constructor
		assertTrue(user1.getcoins() == 0);
		assertTrue(user1.isguest() == false);
		assertTrue(user1.getusername() == "usernameXD");
		assertTrue(user1.getpassword() == "passwordXD");
	}
	
}

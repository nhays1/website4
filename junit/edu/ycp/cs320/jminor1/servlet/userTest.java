package edu.ycp.cs320.jminor1.servlet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import website4.model.usser;

public class userTest {
	
	private usser user1;
	private String username;
	private String password;
	private usser userGuest;
	
	@Before
	public void setup() throws Exception{
		username =  "usernameXD";
		password = "passwordXD";
		user1 = new usser(username, password);
	}
	
	@Test
	public void testGenericUser() {
		assertTrue(user1.getcoins() == 0);
	}
	
}

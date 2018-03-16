package edu.ycp.cs320.jminor.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import website4.model.User;

public class userTest {
	
	private User user1;
	private String username;
	private String password;
	private User userGuest;
	private User userGuest2;
	
	@Before
	public void setup() throws Exception{
		username =  "usernameXD";
		password = "passwordXD";
		user1 = new User(username, password);
	}
	
	@Test
	public void testGenericUser() { //tests real user constructor 2
		assertTrue(user1.getcoins() == 0);
		assertTrue(user1.isguest() == false);
		assertTrue(user1.getusername() == "usernameXD");
		assertTrue(user1.getpassword() == "passwordXD");
	}
	
	@Test
	public void testGuestUser() {
		
		for(int i = 0; i < 1000; i++) {
			userGuest = new User();
			userGuest2 = new User();
			assertTrue(userGuest.getusername() != userGuest2.getusername());
		}
	
		assertTrue(userGuest.getcoins() == 0);
		assertTrue(userGuest.isguest() == true);
		assertTrue(userGuest2.getcoins() == 0);
		assertTrue(userGuest2.isguest() == true);
		assertTrue(userGuest.getusername() != userGuest2.getusername());
	}
	
	@Test
	public void testGettersSetters(){
		user1.setcoins(100);
		user1.setemail("theDude@gmail.gov");
		user1.setpassword("doggy");
		user1.setusername("itsYaBoi69");
		assertTrue(user1.getcoins() == 100);
		assertTrue(user1.getemail() == "theDude@gmail.gov");
		assertTrue(user1.getpassword() == "doggy");
		assertTrue(user1.getusername() == "itsYaBoi69");
	}
	
	
}

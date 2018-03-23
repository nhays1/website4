package edu.ycp.cs320.jminor.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import website4.model.usser;

public class userTest {
	
	private usser user1;
	private String username;
	private String password;
	private usser userGuest;
	private usser userGuest2;
	String email = "email";


	private ArrayList<usser> bList;
	
	@Before
	public void setup() throws Exception{
		username =  "usernameXD";
		password = "passwordXD";
		user1 = new usser(username, password);
		bList = new ArrayList<usser>();
	}
	
	@Test
	public void testGenericUser() { //test test
		assertTrue(user1.getcoins() == 0);
		assertTrue(user1.isguest() == false);
		assertTrue(user1.getusername() == "usernameXD");
		assertTrue(user1.getpassword() == "passwordXD");
	}
	
	@Test
	public void testGuestUser() {
		
		for(int i = 0; i < 1000; i++) {
			userGuest = new usser();
			userGuest2 = new usser();
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
		bList.add(userGuest);
		user1.setcoins(100);
		user1.setemail("theDude@gmail.gov");
		user1.setpassword("doggy");
		user1.setusername("itsYaBoi69");
		user1.addtoblacklist(userGuest.getuserid());
		assertTrue(user1.getblacklist().get(0) == bList.get(0).getuserid());
		assertTrue(user1.getcoins() == 100);
		assertTrue(user1.getemail() == "theDude@gmail.gov");
		assertTrue(user1.getpassword() == "doggy");
		assertTrue(user1.getusername() == "itsYaBoi69");
	}
}

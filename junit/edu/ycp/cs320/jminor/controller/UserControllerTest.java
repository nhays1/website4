package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import website4.controller.UserController;
import website4.controller.createUser;
import website4.model.usser;

public class UserControllerTest {
	
	usser user = new usser();
	UserController userCrl = new UserController();
	
	
	
	
	
	//@Before
	public void setup() throws Exception{
		ArrayList<usser> userList = new ArrayList<usser>();
		userList.add(new usser("user1", "pass1", "email1"));
		userList.add(new usser("user2", "pass2", "email2"));
		userList.add(new usser("user3", "pass3", "email3"));
		userList.add(new usser("user4", "pass4", "email4"));
		userList.add(new usser("user5", "pass5", "email5"));
	}
	
	
	
	@Test
	public void testCreateNewUser() throws SQLException { //tests isValid()
		
		String username = "username";
		String password = "password";
		String email = null;
		
		assertFalse(userCrl.isValid(username, password, email));
		
		userCrl.createUser(username, password, email);
		
		username = null;
		email = "email";
		
		userCrl.createUser(username, password, email);
		
		assertFalse(userCrl.isValid(username, password, email));
		
		username = "username";
		password = null;
		
		userCrl.createUser(username, password, email);
		userCrl.isValid(username, password, email);
		
		assertFalse(userCrl.isValid(username, password, email));
		
		username = "username";
		password = "password";
		email = "email";
		
		userCrl.createUser(username, password, email);
		
		assertTrue(userCrl.isValid(username, password, email));
		
		
		
	}
	
}

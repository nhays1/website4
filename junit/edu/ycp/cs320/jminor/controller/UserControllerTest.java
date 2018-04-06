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

	
	
	
	
	@Before
	public void setup() throws Exception{
		ArrayList<usser> userList = new ArrayList<usser>();
		userList.add(new usser("user1", "pass1", "email1"));
		userList.add(new usser("user2", "pass2", "email2"));
		userList.add(new usser("user3", "pass3", "email3"));
		userList.add(new usser("user4", "pass4", "email4"));
		userList.add(new usser("user5", "pass5", "email5"));
		
	}
	
	
	
	@Test
	//This tests createUser and isValid
	public void testCreateNewUser() throws SQLException {
		
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
	
	
	@Test
	public void testModifyPassword() {
		userCrl.setModel(user);
		
		String password = "testPassword";
		String newPass = "newPassword";
		
		user.setpassword(password);
		
		assertTrue(user.getpassword() == "testPassword");
		
		userCrl.modifyPassword(newPass);
		
		assertEquals(user.getpassword(), newPass);	
	}
	
	@Test
	public void testModifyUsername() {
		userCrl.setModel(user);
		
		String username = "testUsername";
		String newUsername = "newUsername";
		
		user.setusername(username);
		
		//assertEquals(user.getusername(), username);
		
		userCrl.modifyUsername(newUsername);
		//user.setusername(newUsername);
		assertEquals(user.getusername(), newUsername);
		
		
	}
	
	@Test
	public void testCheckPasswordLength() {
		
		String shortPassword = "short";
		String longPassword = "thisPasswordIsOver20Characters";
		String goodPassword ="goodPassword";
		
		//Test for short password
		assertFalse(userCrl.checkPasswordLength(shortPassword));
		//Test for long password
		assertFalse(userCrl.checkPasswordLength(longPassword));
		//Test for correct length password
		assertTrue(userCrl.checkPasswordLength(goodPassword));	
	}

	@Test
	public void testCheckUsernameLength() {
		
		String shortUser = "short";
		String longUser = "thisIsAReallyLongUsername";
		String goodUser = "Goose123";
		
		//Test for short user name
		assertFalse(userCrl.checkUsernameLength(shortUser));
		//Test for long user name
		assertFalse(userCrl.checkUsernameLength(longUser));
		//Test for correct length user name
		assertTrue(userCrl.checkUsernameLength(goodUser));
	}
	
	
	@Test
	public void testLogUserIn() {
		userCrl.setModel(user);
		
		userCrl.loguserin("user3", "pass3");
		
		
	}
	
	@Test
	public void testGetUserByID() {
		userCrl.setModel(user);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

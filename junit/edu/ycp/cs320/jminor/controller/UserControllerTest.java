package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import website4.controller.UserController;

import website4.model.usser;

public class UserControllerTest {
	
	usser user = new usser();
	UserController userCrl = new UserController();

	
	
	
	
	@Before
	public void setup() throws Exception{
		ArrayList<usser> userList = new ArrayList<usser>();
		userList.add(new usser("user123", "pass112", "email1"));
		userList.add(new usser("user232", "pass221", "email2"));
		userList.add(new usser("user323", "pass312", "email3"));
		userList.add(new usser("user423", "pass412", "email4"));
		userList.add(new usser("user523", "pass521", "email5"));
		
	}
	
	
	
	@Test
	//This tests createUser and isValid
	public void testCreateNewUser() throws SQLException {
		
		String username = "username";
		String password = "password";
		String email = null;
		
		assertFalse(userCrl.isValid(username, password, email));
		
		try {
			userCrl.createUser(username, password, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		username = null;
		email = "email";
		
		try {
			userCrl.createUser(username, password, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		assertFalse(userCrl.isValid(username, password, email));
		
		username = "username";
		password = null;
		
		try {
			userCrl.createUser(username, password, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		userCrl.isValid(username, password, email);
		
		assertFalse(userCrl.isValid(username, password, email));
		
		username = "username";
		password = "password";
		email = "email";
		
		try {
			userCrl.createUser(username, password, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
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

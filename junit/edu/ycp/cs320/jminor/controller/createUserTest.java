package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import website4.controller.createUser;
import website4.model.usser;

public class createUserTest {
	
	
	createUser newUser = new createUser();
	
	
	
	
	//@Before
	
	
	@Test
	public void testCreateNewUser() throws SQLException { //tests isValid()
		
		String username = "username";
		String password = "password";
		String email = null;
		newUser.createNewUser(username, password, email);
		boolean testIsValid = newUser.isValid(username, password, email);
		assertFalse(testIsValid);
		
		username = null;
		email = "email";
		newUser.createNewUser(username, password, email);
		testIsValid = newUser.isValid(username, password, email);
		assertFalse(testIsValid);
		
		username = "username";
		password = null;
		newUser.createNewUser(username, password, email);
		testIsValid = newUser.isValid(username, password, email);
		assertFalse(testIsValid);
		
		password = "password";
		newUser.createNewUser(username, password, email);
		testIsValid = newUser.isValid(username, password, email);
		assertTrue(testIsValid);
		
		
		
	}
	
}

package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import website4.controller.loginverification;
import website4.model.usser;



public class LoginVerificationTest {
	private usser correctuser;
	private usser incorrectuser;
	private usser incorrectpassword;
	private usser logedin1,logedin2,logedin3;
	private loginverification loger;
	
	
	@Before
	public void setUp() {
		loger= new loginverification() ;
		
		 correctuser= new usser("jake","123");
		 incorrectuser= new usser("jske","1235");
		 incorrectpassword= new usser("jake","skanfurnsgiemdtenale023n!");
		
		 
		 logedin1= loger.loguserin(correctuser.getusername(), correctuser.getpassword());
		 logedin2=loger.loguserin(correctuser.getusername(), incorrectuser.getpassword());
		 logedin3=loger.loguserin(incorrectpassword.getusername(), incorrectpassword.getpassword());
		 
	}
	
	@Test
	public void testLogin() {
		
		assertEquals(correctuser.getusername(),logedin1.getusername());
		
		assertEquals(null,logedin2);
		//assertEquals(null,logedin2.getusername());
		
		
		assertEquals(null,logedin3);
		//assertEquals(null,logedin3.getusername());
		
	}
	
	@Test
	public void testLoginfirstusserbug() {
		incorrectpassword=new usser("2ake","skanfurnsgiemdtenale023n!");
		logedin3=loger.loguserin(incorrectpassword.getusername(), incorrectpassword.getpassword());
		
		
		assertEquals(null,logedin3);
	//	assertEquals(null,logedin3.getusername());
		
		incorrectpassword=new usser("j0ke","123");
		logedin3=loger.loguserin(incorrectpassword.getusername(), incorrectpassword.getpassword());
		
		assertEquals(null,logedin3);
		//assertEquals(null,logedin3.getusername());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

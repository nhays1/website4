package edu.ycp.cs320.jminor.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import website4.model.post;
import website4.model.usser;

public class posttest {
	post incomming;
	post empty;


	
	
	@Before
	public void setup() throws Exception{
		
		
		empty=new post();
		incomming=new post(111111111L,"jakson","tehe achbcdx C");
		
	}
	
	@Test
	public void testgetandset() { //test test
		empty.setmils_time(22222222L);
		assertEquals(22222222L,empty.Getmils_time());
		
		empty.setpost("tetstte");
		assertEquals("tetstte",empty.Getpost());
		
		empty.setusername("jminot");
		assertEquals("jminot", empty.Getusername());
		
		empty.setuserid(202020);
		assertEquals(202020,empty.getuserid());
	}
	
	
	@Test
	public void testconstructor() {
		assertEquals(111111111L,incomming.Getmils_time());
		
		assertEquals("jakson",incomming.Getusername());
		
		assertEquals("tehe achbcdx C",incomming.Getpost());
		
	}
	
	
	
	
	
	
	
}

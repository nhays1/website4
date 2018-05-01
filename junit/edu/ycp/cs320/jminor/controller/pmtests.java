package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import website4.controller.UserController;
import website4.controller.chatcontroler;
import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.databsecontroler.InitDatabase;
import website4.model.post;
import website4.model.usser;




public class pmtests {
	private chatcontroler chat;
	private post incomingpost;
	ArrayList<post> posts;
	long now;
	usser user1,user2;
	UserController ctrl;
	
	@Before
	public void setUp() {
		ctrl=new UserController();
		chat = new chatcontroler();
		now=Instant.now().toEpochMilli();
		user1=ctrl.createguestuser();
		user2=ctrl.createguestuser();
		
	}
	
	@Test
	public void testgetpmid() {
		
		
	}
	
	
	
	
	
}

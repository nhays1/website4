package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import website4.controller.chatcontroler;

import website4.model.post;




public class chatcontrolertest {
	private chatcontroler chat;
	private post incomingpost;
	ArrayList<post> posts;
	long now;
	
	@Before
	public void setUp() {
		chat = new chatcontroler();
		now=Instant.now().toEpochMilli();
		incomingpost=new post(now,"jake","this si a psot");
		
	}
	
	@Test
	public void testaddtochat() {
		
		//chat.makenewpost(now,2 , chatinput);
		chat.makenewpost(incomingpost.Getmils_time(), 2, incomingpost.Getpost(),"general");
		
		posts=(ArrayList<post>) chat.Getchat(0,"general");
		Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(posts));
		
		assertEquals(incomingpost.Getusername() ,posts.get(posts.size()-1).Getusername()); 
		
		assertEquals(incomingpost.Getmils_time() ,posts.get(posts.size()-1).Getmils_time());
		assertEquals(incomingpost.Getpost() ,posts.get(posts.size()-1).Getpost());
		
		
		
		//assertNotEquals(incomingpost.Getusername() ,posts.get(posts.size()-2).Getusername());
		
		assertNotEquals(incomingpost.Getmils_time() ,posts.get(posts.size()-2).Getmils_time());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

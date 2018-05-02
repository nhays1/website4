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
import website4.database.Triplet;
import website4.databsecontroler.InitDatabase;
import website4.model.post;
import website4.model.usser;




public class pmtests {
	private chatcontroler chat;
	ArrayList<post> posts;
	long now, later;
	usser user1,user2;
	UserController ctrl;
	int chat1;
	
	@Before
	public void setUp() {
		later=2;
		ctrl=new UserController();
		chat = new chatcontroler();
		now=Instant.now().toEpochMilli();
		user1=ctrl.createguestuser();
		user2=ctrl.createguestuser();
		chat1=chat.getpmid(user1.getuserid(), user2.getuserid());
		chat.posttopm(now, user1.getuserid(), "post111", chat1);
		chat.posttopm(now+later, user2.getuserid(), "post222", chat1);
		chat.posttopm(now+later, user2.getuserid(), "post222", chat1);
	}
	
	@Test
	public void testgetpmid() {
		int chatid;
		chatid=chat.getpmid(user1.getuserid(), user2.getuserid());
		assertEquals(chat1,chatid);
		
	}
	
	@Test
	public void testposttopm() {
		chat.posttopm(now, user1.getuserid(), "post111", chat1);
		chat.posttopm(now, user2.getuserid(), "post222", chat1);
	    ArrayList<post> us1=(ArrayList<post>) chat.gotopm(chat1, user1.getuserid());
	    ArrayList<post> us2=(ArrayList<post>)chat.gotopm(chat1, user1.getuserid());
	    assertEquals(us1.get(0).Getpost(),us2.get(0).Getpost());
	    assertEquals(us1.get(1).Getpost(),us2.get(1).Getpost());
		
	}
	
	@Test
	public void testgetpmlist() {
	    ArrayList<Triplet<String, Integer,Integer>> us1= (ArrayList<Triplet<String, Integer, Integer>>) chat.getpmlist(user1.getuserid());
	    ArrayList<Triplet<String, Integer,Integer>> us2= (ArrayList<Triplet<String, Integer, Integer>>) chat.getpmlist(user1.getuserid());
	    assertEquals((Integer) 0 ,us2.get(0).getThird());
	    assertEquals((Integer) 0 ,us1.get(0).getThird());
	    assertEquals(0 ,chat.getunreadpms(user1.getuserid()));
	    assertEquals(us2.get(0).getSecond() ,us1.get(0).getSecond());
		
	}
	
	
	
}

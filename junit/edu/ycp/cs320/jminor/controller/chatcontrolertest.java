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




public class chatcontrolertest {
	private chatcontroler chat;
	private post incomingpost;
	ArrayList<post> posts;
	long now;
	usser user;
	UserController ctrl;
	
	@Before
	public void setUp() {
		ctrl=new UserController();
		chat = new chatcontroler();
		now=Instant.now().toEpochMilli();
		user=ctrl.createguestuser();
		incomingpost=new post(now,user.getusername(),"this si a psot");
		
	}
	
	@Test
	public void testaddtochat() {
		
		//chat.makenewpost(now,2 , chatinput);
		chat.makenewpost(incomingpost.Getmils_time(), user.getuserid(), incomingpost.Getpost(),"general");
		
		posts=(ArrayList<post>) chat.Getchat(0,"general",0);
		Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(posts));
		
		assertEquals(incomingpost.Getusername() ,posts.get(posts.size()-1).Getusername()); 
		
		assertEquals(incomingpost.Getmils_time() ,posts.get(posts.size()-1).Getmils_time());
		assertEquals(incomingpost.Getpost() ,posts.get(posts.size()-1).Getpost());
		
		
		
		//assertNotEquals(incomingpost.Getusername() ,posts.get(posts.size()-2).Getusername());
		
		assertNotEquals(incomingpost.Getmils_time() ,posts.get(posts.size()-2).Getmils_time());
	}
	
	
	@Test
	public void testaddtoblacklist() {
		chat.addtoblacklist(user.getuserid(), 1111);
		chat.addtoblacklist(user.getuserid(), 222);
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		try {
			//db.addusertodb(222, "test222", "password", "email", 0);
			//db.addusertodb(1111, "test1111", "password", "email", 0);
		}
		catch (Exception e) {
			System.out.println("users already created");
		}
		chat.makenewpost(now, 1111, "post test", "towerdef1");
		chat.makenewpost(now, 1111, "post teswt", "towerdef1");
		chat.makenewpost(now, 2, "dont block", "towerdef1");
		chat.makenewpost(now, 222, "post tes3t", "towerdef1");
		chat.makenewpost(now, 222, "post tested4", "towerdef1");
		
		ArrayList<post> cht= (ArrayList<post>) chat.Getchat(0, "towerdef1", user.getuserid());
		
		for (int i=0;i< cht.size();i++) {
			assertNotEquals(1111 ,cht.get(i).getuserid());
			assertNotEquals(222 ,cht.get(i).getuserid());
			System.out.println(i);
		}
		assertEquals("dont block" ,cht.get(0).Getpost()); 
		
	}
	
	
	
	@Test
	public void chatnametoindex() {
		int gen=chat.cahtnametoindex("general");
		int tdef=chat.cahtnametoindex("towerdef1");
		assertEquals(0 ,gen);
		assertEquals(1 ,tdef);
	}
	
	@Test
	public void testaddchatdne() {
		String error = null;
		try {
			chat.addusertochat(user.getuserid(), "ss");
		}
		catch (Exception e) {
			error=e.getMessage();
		}
		assertEquals("the chat -ss- does not exsits \ntry creating it" ,error);
		
		
	}
	@Test
	public void testaddchatexisting() {
		String error = null;
		try {
			chat.creatuserchat(user.getuserid(), "ssdd");
			chat.creatuserchat(user.getuserid(), "ssdd");
		}
		catch (Exception e) {
			error=e.getMessage();
		}
		assertEquals("the chat ssdd already exsits " ,error);
		
		chat.addusertochat(user.getuserid(), "ssdd");
		
		List<String> chats= chat.getuserchatnames(user.getuserid());
		
		assertEquals(true ,chats.contains("ssdd"));
		
	}
	
	
	@Test
	public void testcreateuserchat() {
		String error = null;
		try {
			chat.creatuserchat(user.getuserid(), "null");
			chat.creatuserchat(user.getuserid(), "null");
		}
		catch (Exception e) {
			error=e.getMessage();
		}
		assertEquals("the chat null already exsits " ,error);
		
		
	}
	
	
	
	
}

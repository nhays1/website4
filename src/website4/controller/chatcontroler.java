package website4.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.databsecontroler.InitDatabase;
//	import website4.database.IDatabase;
//	import website4.databsecontroler.InitDatabase;
import website4.model.post;


public class chatcontroler {

	public chatcontroler() {
		
	
	}
	
	
	
	
	
	
	
	//TODO this controler will take username and password from servlet and check or reject the information
	// if the information is invalid it should throw an exception to the servlet which will send the message to the user
	
	//for now this method will contain an aray but later that will be replased by a database
	/**
	 * this method should be called from the servlets to add new posts to the total
	 * for now it is an araylist but later this method will interface with the data base
	 * it will also validate the post eg makesure its not to long, contains no html/js/sql, ect 
	 * 
	 * @param mils_time
	 * @param username
	 * @param post
	 */
	public void makenewpost(long mils_time ,int userid,String posttext) {
		if(posttext!=""&&posttext!=null) {
			//other validations will go here
			
			
			
			
			
			InitDatabase.init(1);
			IDatabase db = DatabaseProvider.getInstance();
			db.addpost(mils_time, userid, posttext);
			
		}	
	}
	
	
	public List<post> Getchat(){
		
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getposts_no_blacklist(0,5);
	
		//return posts;
	}
	
	
	
	
	
	
	
	
}

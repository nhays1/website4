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
	public void makenewpost(long mils_time ,int userid,String posttext,String chatname) {
		if(posttext!=""&&posttext!=null) {
			//other validations will go here
			
			InitDatabase.init(1);
			IDatabase db = DatabaseProvider.getInstance();
			db.addpost(mils_time, userid, posttext,chatname);
			
		}	
	}
	
	
	public List<post> Getchat(int chatindex,String chatname){
		
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getposts_no_blacklist(0 ,chatindex+10,chatname,2);
	
	}
	/**the chat is specified outside the db by its name
	 * in the database it is specified by an int this is used to relate the 2
	 * 
	 * @param Chatnmae
	 * @return
	 */
	public int cahtnametoindex(String Chatnmae) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.chatnametoid(Chatnmae);
				
	}
	
	
	
	
	
	
}

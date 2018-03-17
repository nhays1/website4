package website4.controller;



import java.util.ArrayList;

import website4.model.post;


public class chatcontroler {
	private post incomingpost;
	private ArrayList<post> chatposts=new ArrayList<post>();
	private int test=7;
	public chatcontroler() {
		
		incomingpost=new post(15210791386L,"jminor","this cant be right");
		chatposts.add(incomingpost);
		incomingpost=new post(1511079138360L,"jminor","iim from teh past");
		chatposts.add(incomingpost);
		incomingpost=new post(1521079138360L,"jminor","this is a post with some text");
		chatposts.add(incomingpost);
		incomingpost=new post(1521079138360L,"jminor","so is this");
		chatposts.add(incomingpost);
		
	
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
	public void makenewpost(long mils_time ,String username,String posttext) {
		if(posttext!=""&&posttext!=null) {
			//other validations will go here
			
			
			
			incomingpost=new post(mils_time,username,posttext);
			chatposts.add(incomingpost);

		}	
	}
	
	
	public ArrayList<post> Getchat(){
		return chatposts;

		
	}
	
	
	public int getval(){
		return test;
		
		
		
	}
	
	
	
	
	
}

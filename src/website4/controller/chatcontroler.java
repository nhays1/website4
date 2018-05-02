package website4.controller;



import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.database.Triplet;
import website4.databsecontroler.InitDatabase;
//	import website4.database.IDatabase;
//	import website4.databsecontroler.InitDatabase;
import website4.model.post;


public class chatcontroler {

	public chatcontroler() {
		
	
	}
	
	
	public static void main(String[] args) throws IOException {
		//String name="towerdef1";
		Gson gson = new GsonBuilder().create();
		//String jsonchstpost = gson.toJson(addtouserscores(name,4,50));
		//System.out.println(jsonchstpost);
		System.out.println("aver er " );
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		long now=Instant.now().toEpochMilli();
		//now+=86400000;
		//db.updateguestlist(now);
		System.out.println("aver er " );
		
		
		System.out.println(gson.toJson(db.getunreadpms(2, 1,now)));
		//db.posttopm(now, "aerfe ser", 2, s);
		//System.out.println(db.getpm(2, s));
		
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
	
	
	public List<post> Getchat(int chatindex,String chatname,int gettinguserid){
		
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getposts_no_blacklist(0 ,chatindex+10,chatname,gettinguserid);
	
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
	public List<String> getuserchatnames(int userid)throws NoSuchElementException{
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getuserchatnames(userid);
		
	}
	
	public void addusertochat(int userid,String chatname) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.addusertochat(chatname, userid);
	}
	public void creatuserchat(int userid,String chatname) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.createchat(chatname, userid);
		
		
	}
	public int getpmid(int usid1,int usid2) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.creatpm(usid1, usid2);
		return db.getpmid(usid1, usid2);
	}
	
	
	public List<post> gotopm(int pmid, int retrivinguserid){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		System.out.println(pmid);
		db.updatepmacesstime(retrivinguserid, pmid);
		return db.getpm(10, pmid,retrivinguserid);

	}
	public List<post> morepm(int numposts, int pmid,int retrivinguserid){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.updatepmacesstime(retrivinguserid, pmid);
		return db.getpm(numposts, pmid,retrivinguserid);
	}
	
	public void posttopm(long mils_time ,int senderid,String posttext,int pmid) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.posttopm(mils_time, posttext, senderid, pmid);
		db.updatepmacesstime(senderid, pmid);
	}
	public List<Triplet<String, Integer,Integer>> getpmlist(int userid){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getpmlist(userid);
	}
	public void addtoblacklist(int blockerid,int blockieeid) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.addtoblacklist(blockerid, blockieeid);
	}
	public int getunreadpms(int userid){
		ArrayList<Triplet<String, Integer,Integer>> things=(ArrayList<Triplet<String, Integer, Integer>>) getpmlist(userid);
		int total=0;
		for(int i=0;i<things.size();i++) {
			total+=things.get(i).getThird();
		}
		return total;
		
	}
	public boolean checkchatneedupdate(String chatname,long lastknown) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		int chatid=db.chatnametoid(chatname);
		long current = db.getlastposttime(chatid);
		System.out.println("     timedif  "+(lastknown-current));
		if(current>lastknown) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
			
}

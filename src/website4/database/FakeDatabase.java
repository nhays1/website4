package website4.database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import javafx.util.Pair;
import website4.model.post;
import website4.model.usser;



public class FakeDatabase implements IDatabase {
	
	//This code was from https://stackoverflow.com/questions/6010843/java-how-to-store-data-triple-in-a-list?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa



	public List<post> getposts_no_blacklist(int chatindex,int numposts , String Chatname,int gettinguserid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addpost(long mils_time, int userid, String posttext , String Chatname) {
		// TODO Auto-generated method stub
		//Create of list of Triplets
		List<Triplet<Long, Integer, String>> postsList = new ArrayList<Triplet<Long, Integer, String>>();
		
		//Create a Triplet that uses the method's arguements
		Triplet<Long, Integer, String> posts = new Triplet<Long, Integer, String>(mils_time, userid, posttext);
		
		//Add post to postsList
		postsList.add(posts);
	}

	public usser getuser_by_id(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addusertodb(int userid, String username, String password, String email, int coins) {
		// TODO Auto-generated method stub
		
	}

	public boolean checkdbcontainsuserid(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkdbcontainsusername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Map.Entry<String, Integer>> getper_game_scores(String nameofthegame) {
		// TODO Auto-generated method stub
		return null;
		
		//Map.Entry<String,Integer> skore =new AbstractMap.SimpleEntry<Integer, Integer>(usid, score);// how to create new entry
	}

	public List<Entry<String, Integer>> addscoretogmaedb(String nameofthegame, int userid, int score, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> getperuserscores(String nameofthegame, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addtouserscores(String nameofthegame, int userid, int score) {
		// TODO Auto-generated method stub
		
	}

	public void createUser(String userName, String password, String email) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public boolean isValid(String userName, String password, String email) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void addtoguestlist(int userid, long timelogedin) {
		// TODO Auto-generated method stub
		
	}

	public void updateguestlist(long now) {
		// TODO Auto-generated method stub
		
	}

	public List<Entry<Integer, Long>> getguestlist() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isguest(int userid) {
		// TODO Auto-generated method stub
		return false;
	}

	public int chatnametoid(String chatname) {
		// TODO Auto-generated method stub
		return 0;
	}

	public usser loguserin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public String chatidtoname(int chatid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addusertochat(String chatname, int userid) {
		// TODO Auto-generated method stub
		
	}

	public void createchat(String chatname, int userid) {
		// TODO Auto-generated method stub
		
	}

	public List<Integer> getuserchataxcess(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getuserchatnames(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer creatpm(int user1, int user2) {
		return null;
		// TODO Auto-generated method stub
		
	}

	

	public List<post> posttopm(long mils_time, String posttext, int senderid, int pmid) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getpmid(int user1, int user2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void createUser(String userName, String password, String email, String userid) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public List<Triplet<String, Integer,Integer>> getpmlist(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getusernamebyid(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<post> getpm(int numposts, int pmid, int retriverid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatepmacesstime(int userid, int pmid) {
		// TODO Auto-generated method stub
		
	}

	public int getunreadpms(int retriverid, int pmid, long lastacessed) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addtoblacklist(int blockerid, int blockieid) {
		// TODO Auto-generated method stub
		
	}

	public long getlastposttime(int chatid) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	

	
}

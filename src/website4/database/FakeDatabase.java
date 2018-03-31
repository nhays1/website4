package website4.database;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import website4.model.post;
import website4.model.usser;



public class FakeDatabase implements IDatabase {



	public List<post> getposts_no_blacklist(int chatindex,int numposts) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addpost(long mils_time, int userid, String posttext) {
		// TODO Auto-generated method stub
		
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

	
	
	

	
}

package website4.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.util.Pair;
import website4.model.post;
import website4.model.usser;



public class FakeDatabase implements IDatabase {
	
	//This code was from https://stackoverflow.com/questions/6010843/java-how-to-store-data-triple-in-a-list?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa



	public List<post> getposts_no_blacklist(int chatindex,int numposts) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addpost(long mils_time, int userid, String posttext) {
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

	
	
	

	
}

package website4.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import website4.model.post;
import website4.model.usser;

public class InitialData {
	public static List<post> getposts() throws IOException {
		List<post> postlist = new ArrayList<post>();
		ReadCSV readposts = new ReadCSV("post.csv");
		try {
			// auto-generated primary key for authors table
			
			while (true) {
				List<String> tuple = readposts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				post post = new post();
				
				
				//i.next();
				post.setuserid(Integer.parseInt(i.next()));
				post.setusername(i.next());
				//System.out.println(post.Getusername());
				post.setmils_time(Long.parseLong(i.next()));
				//System.out.println(post.Getmils_time());
				post.setpost(i.next());
				
				
				postlist.add(post);
			}
			return postlist;
		} finally {
			readposts.close();
		}
	}
	
	public static List<usser> getusers() throws IOException {
		List<usser> userlist = new ArrayList<usser>();
		ReadCSV readusers = new ReadCSV("user.csv");
		try {
			// auto-generated primary key for books table
			
			while (true) {
				List<String> tuple = readusers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				usser user = new usser();
				user.setuserid(Integer.parseInt(i.next()));
				user.setusername(i.next());
				user.setpassword(i.next());
				user.setcoins(Integer.parseInt(i.next()));
				user.setemail(i.next());
				
				
				userlist.add(user);
			}
			return userlist;
		} finally {
			readusers.close();
		}
	}
}

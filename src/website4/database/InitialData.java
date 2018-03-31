package website4.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import website4.model.per_game_scores;
import website4.model.per_user_scores;
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
	
	public static List<per_user_scores> getuserscores() throws IOException {
		List<per_user_scores> userscorelist = new ArrayList<per_user_scores>();
		ReadCSV readper_user_scores = new ReadCSV("per_user_scores.csv");
		try {
			// auto-generated primary key for authors table
			
			while (true) {
				List<String> tuple = readper_user_scores.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				per_user_scores score =new per_user_scores();
				ArrayList<Integer> scores=new ArrayList<Integer>() ;
				
				
				score.setusid(Integer.parseInt(i.next()));
				score.setnameofgame(i.next());
				scores.add(Integer.parseInt(i.next()));//0
				scores.add(Integer.parseInt(i.next()));//1
				scores.add(Integer.parseInt(i.next()));//2
				scores.add(Integer.parseInt(i.next()));//3
				scores.add(Integer.parseInt(i.next()));//4
				scores.add(Integer.parseInt(i.next()));//5
				scores.add(Integer.parseInt(i.next()));//6
				scores.add(Integer.parseInt(i.next()));//7
				scores.add(Integer.parseInt(i.next()));//8
				scores.add(Integer.parseInt(i.next()));//9
				
				score.setscores(scores);
				
				
				userscorelist.add(score);
			}
			return userscorelist;
		} finally {
			readper_user_scores.close();
		}
	}
	public static List<per_game_scores> getgamecores() throws IOException {
		List<per_game_scores> gamescorelist = new ArrayList<per_game_scores>();
		ReadCSV readper_game_scores = new ReadCSV("per_game_scores.csv");
		try {
			// auto-generated primary key for authors table
			
			while (true) {
				List<String> tuple = readper_game_scores.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				per_game_scores score =new per_game_scores();
			
				
				score.setnameofgame(i.next());
				score.setscore(Integer.parseInt(i.next()));
				score.setusid(Integer.parseInt(i.next()));
				
				

				
				gamescorelist.add(score);
			}
			return gamescorelist;
		} finally {
			readper_game_scores.close();
		}
	}
	
	
	
	
	
	
	
	
}

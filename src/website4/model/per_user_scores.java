package website4.model;

import java.util.ArrayList;

public class per_user_scores {
	
	private int usid;
	private ArrayList<Integer> scores;
	private String nameofthegame;
	// onece the data bese is set up the user id will be used as oposed to username 
	
	
	
	
	
	
	public per_user_scores(int usid ,String nameofthegame,ArrayList<Integer>  scores) {
		this.scores=scores;
		this.nameofthegame=nameofthegame;
		this.usid=usid;
	}
	
	public per_user_scores() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void setusid(int usid) {
		this.usid=usid;
	}
	
	public void setnameofgame(String nameofthegame) {
		this.nameofthegame=nameofthegame;
	}
	
	public void setscores(ArrayList<Integer>  scores) {
		this.scores=scores;
	}
	
	public ArrayList<Integer> getscores(){
		return scores;
		
	}
	

	public String getnameofgame(){
		return nameofthegame;
		
	}
	public int getusid(){
		return usid;

	}
	
	
	
	
	
	
	

}

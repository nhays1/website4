package website4.model;



public class per_game_scores {
	
	private int usid;
	private int scores;
	private String nameofthegame;
	// onece the data bese is set up the user id will be used as oposed to username 
	
	
	
	
	
	
	public per_game_scores(int usid ,String nameofthegame,int  scores) {
		this.scores=scores;
		this.nameofthegame=nameofthegame;
		this.usid=usid;
	}
	
	public per_game_scores() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void setusid(int usid) {
		this.usid=usid;
	}
	
	public void setnameofgame(String nameofthegame) {
		this.nameofthegame=nameofthegame;
	}
	
	public void setscore(int  score) {
		this.scores=score;
	}
	
	public int getscore(){
		return scores;
		
	}
	

	public String getnameofgame(){
		return nameofthegame;
		
	}
	public int getusid(){
		return usid;

	}
	
	
	
	
	
	
	

}

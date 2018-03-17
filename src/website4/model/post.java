package website4.model;

public class post {
	
	private int userid;
	private long milstime;
	private String username,post;
	
	// onece the data bese is set up the user id will be used as oposed to username 
	
	
	
	
	
	
	public post(long mils_time ,String username,String post) {
		this.milstime=mils_time;
		this.username=username;
		this.post=post;
	}
	
	public long Getmils_time() {
		return milstime;
	}
	
	public String Getusername() {
		return username;
	}
	
	public String Getpost() {
		return post;
	}
	
	
	
	
	

}

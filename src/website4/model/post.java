package website4.model;

public class post {
	
	private int usid;
	private long mit;
	private String user,post;
	
	// onece the data bese is set up the user id will be used as oposed to username 
	
	
	
	
	
	
	public post(long mils_time ,String username,String post) {
		this.mit=mils_time;
		this.user=username;
		this.post=post;
	}
	
	public post() {
		// TODO Auto-generated constructor stub
	}
	
	public void setmils_time(long mils_time) {
		this.mit=mils_time;
	}
	
	public void setpost(String post) {
		this.post=post;
	}
	
	public void setusername(String username) {
		this.user=username;
	}
	
	public void setuserid(int usserid) {
		this.usid=usserid;
	}
	
	
	
	public long Getmils_time() {
		return mit;
	}
	
	public String Getusername() {
		return user;
	}
	
	public String Getpost() {
		return post;
	}
	
	public int getuserid() {
		return usid;
		
	}
	
	
	

}

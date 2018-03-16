package website4.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class User {

	private String username, email;
	
	private String password;
	
	private int coins;
	
	private ArrayList<User>  blacklist;
	
	private boolean isguest;
	
	/**
	 * WARNING!!!!!!! this method does not check
	 *  incoming data, use only after caller has
	 *  Verified data
	 *  
	 * constructor, takes username and password of new user
	 * then logs them in sets coins to zero and creates empty blacklist
	 * 
	 * @param username
	 * @param password
	 */
	
	public User(String username,String password){
		this.username=username;
		this.password=password;
		coins=0;
		blacklist=new ArrayList<User>();
		isguest=false;
	}
	
	/**
	 * this constructor creates a guest user with a random username of the form
	 * guest_#
	 * 
	 * 
	 * 
	 */
	
	
	public User() {
		this.username="guest_";
		Random rand = new Random();
		int randnum= rand.nextInt(131072);
		String guestnum=String.valueOf(randnum) ;
		this.username+=guestnum;
		coins=0;
		blacklist=new ArrayList<User>();
		isguest=true;
	}
	
	
	
	
	
	public boolean isguest() {
		return isguest;
	}
	
	public int getcoins() {
		return coins;
	}
	
	public void setcoins(int coins) {
		this.coins=coins;
	}
	public void addtoblacklist(User toblock) {
		blacklist.add(toblock);
	}
	/**
	 * throws NoSuchElementException if the input 
	 * user is not on the blacklist 
	 * otherwise that user is removed from the blacklist
	 * @param tounblock
	 */
	public void removefromblacklist(User tounblock) {
		if(blacklist.contains(tounblock)) {
			blacklist.remove(blacklist.indexOf(tounblock));		
		}
		else {
			throw new NoSuchElementException();
		}
	}
	/**
	 * returns an arraylist of user objects
	 * @return
	 */
	public ArrayList<User> getblacklist(){
		return blacklist;
	
	}
	
	public String getusername(){
		return username;
	}
	/**
	 * WARNING!!!!!! this method does not check
	 *  incoming data, use only after caller has
	 *  Verified data
	 * 
	 * @param usuername
	 */
	public void setusername(String usuername) {
		this.username=usuername;
	}
	
	public String getemail(){
		return email;
	}
	/**
	 * WARNING!!!!!! this method does not check
	 *  incoming data, use only after caller has
	 *  Verified data
	 * @param email
	 */
	public void setemail(String email) {
		this.email=email;
	}

	
	public String getpassword(){
		return password;
	}
	/**
	 * WARNING!!!!!! this method does not check
	 *  incoming data, use only after caller has
	 *  Verified data
	 * @param password
	 */
	public void setpassword(String password) {
		this.password=password;
	}
	
	
}

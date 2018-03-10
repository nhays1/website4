package website4.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class usser {

	private String username, email;
	
	private String password;
	
	private int coins;
	
	private ArrayList<usser>  blacklist;
	
	private boolean islogedin;
	
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
	public usser(String username,String password){
		this.username=username;
		this.password=password;
		coins=0;
		blacklist=new ArrayList<usser>();
		islogedin=true;
	}
	public void login() {
		islogedin=true;
	}
	
	public void logout() {
		islogedin=false;
	}
	
	public boolean islogedin() {
		return islogedin;
	}
	
	public int getcoins() {
		return coins;
	}
	
	public void setcoins(int coins) {
		this.coins=coins;
	}
	public void addtoblacklist(usser toblock) {
		blacklist.add(toblock);
	}
	/**
	 * throws NoSuchElementException if the input 
	 * user is not on the blacklist 
	 * otherwise that user is removed from the blacklist
	 * @param tounblock
	 */
	public void removefromblacklist(usser tounblock) {
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
	public ArrayList<usser> getblacklist(){
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

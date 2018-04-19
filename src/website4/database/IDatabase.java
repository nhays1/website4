package website4.database;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import website4.model.post;
import website4.model.usser;

public interface IDatabase {
	/**this method will do blacklist for the user specified by gettinguserid
	 * the black list is its own db this method will handle it
	 * gets the numposts posts starting at index chatindex for the chat named Chatname 
	 * 
	 * @param chatindex
	 * @param numposts
	 * @param Chatname
	 * @param gettinguserid
	 * @return
	 */
	public List<post> getposts_no_blacklist(int chatindex,int numposts, String Chatname,int gettinguserid);
	/**
	 * adds a post to to the db posted at the time mils_time posted by the suer specified by userid in the 
	 * chat specified by Chatname
	 * @param mils_time
	 * @param userid
	 * @param posttext
	 * @param Chatname
	 */
	public void addpost(long mils_time ,int userid,String posttext, String Chatname);
	/**in the post db chat name is stored as an int for faster comparing this method retrives the int rfered to by chatname
	 * 
	 * @param chatname
	 * @return
	 */
	public int chatnametoid (String chatname);
	
	public String chatidtoname(int chatid);
	
	public void addusertochat(String chatname,int userid);
	
	public void createchat(String chatname,int userid);
	/**intended for sesion verification returns usser specied by id
	 * 
	 * @param id
	 * @return
	 */
	public usser getuser_by_id(int id);
	/** fills all db ccolums in the user db with the specified information
	 * 
	 * @param userid
	 * @param username
	 * @param password
	 * @param email
	 * @param coins
	 */
	public void addusertodb(int userid,String username,String password,String email,int coins);
	/** used to keep track of which users are guests and when the loged in 
	 * 
	 * @param userid
	 * @param timelogedin
	 */
	public void addtoguestlist(int userid,long timelogedin);
	/**returns the guest list
	 * 
	 * @return
	 */
	public List<Map.Entry<Integer, Long>> getguestlist();
	/**!!!! WIP !!!!
	 * runs through guest list and sees if any of the guests have been loged in for a certian amount of time since long now
	 * 
	 * @param now
	 */
	public void updateguestlist(long now);
	/**
	 * returns true if userid exists in database
	 * @param id
	 * @return
	 */
	public boolean checkdbcontainsuserid(int id);
	/**
	 * returns true if username exists in database
	 * @param username
	 * @return
	 */
	public boolean checkdbcontainsusername(String username);
	/**
	 * key= score
	 * value = userid
	 * @param nameofthegame
	 * @return
	 */
	public List<Map.Entry<String, Integer>> getper_game_scores(String nameofthegame);
	/**
	 * this method will add the score specified to the per_game db along with the userid who scored it
	 * for convienence it also returns the updated score list for this game
	 * 
	 * 
	 * @param nameofthegame
	 * @param userid
	 * @param score
	 * @param username
	 * @return
	 */
	public List<Map.Entry<String, Integer>> addscoretogmaedb(String nameofthegame,int userid,int score,String username );
	/**returns the user scores for this game scores 
	 * 
	 * @param nameofthegame
	 * @param userid
	 * @return
	 */
	public List<Integer> getperuserscores(String nameofthegame,int userid);
	/**adds the score to this users row for this game
	 * 
	 * @param nameofthegame
	 * @param userid
	 * @param score
	 */
	public void addtouserscores(String nameofthegame,int userid,int score);
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 * @throws SQLException
	 */
	public void createUser(String userName, String password, String email, String userid)throws SQLException;
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean isValid(final String userName, String password,final String email) throws SQLException;
	/**returs true if this user id is in the guest list
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isguest(int userid);
	/**logs in a user with this username and password if none are found return null
	 * if password incorrect return null
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public usser loguserin(String username,String password);
	/**returns a list of all the chats this user has axcess to
	 * 
	 * 
	 * @param userid
	 * @return
	 */
	public List<Integer> getuserchataxcess(int userid);
	/**
	 * returns a list of all the names of chats this user has axcess to
	 * 
	 * @param userid
	 * @return
	 */
	public List<String> getuserchatnames(int userid);
}

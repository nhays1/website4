package website4.database;


import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import website4.model.post;
import website4.model.usser;

public interface IDatabase {
	
	public List<post> getposts_no_blacklist(int chatindex,int numposts);
	public void addpost(long mils_time ,int userid,String posttext);
	public usser getuser_by_id(int id);

	public void addusertodb(int userid,String username,String password,String email,int coins);
	public void addtoguestlist(int userid,long timelogedin);
	public List<Map.Entry<Integer, Long>> getguestlist();
	public void updateguestlist(long now);
	public boolean checkdbcontainsuserid(int id);
	public boolean checkdbcontainsusername(String username);
	/**
	 * key= score
	 * value = userid
	 * @param nameofthegame
	 * @return
	 */
	public List<Map.Entry<String, Integer>> getper_game_scores(String nameofthegame);//List<Map.Entry<Integer, Integer>>
	public List<Map.Entry<String, Integer>> addscoretogmaedb(String nameofthegame,int userid,int score,String username );
	public List<Integer> getperuserscores(String nameofthegame,int userid);
	public void addtouserscores(String nameofthegame,int userid,int score);
	public void createUser(String userName, String password, String email)throws SQLException;
	public boolean isValid(final String userName, String password,final String email) throws SQLException;
}

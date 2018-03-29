package website4.database;

import java.util.ArrayList;
import java.util.List;


import website4.model.post;
import website4.model.usser;

public interface IDatabase {
	
	public List<post> getposts_no_blacklist(int chatindex,int numposts);
	public void addpost(long mils_time ,int userid,String posttext);
	public usser getuser_by_id(int id);
}

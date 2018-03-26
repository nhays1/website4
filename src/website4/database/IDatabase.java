package website4.database;

import java.util.ArrayList;
import java.util.List;


import website4.model.post;

public interface IDatabase {
	
	public List<post> getposts_no_blacklist();
}

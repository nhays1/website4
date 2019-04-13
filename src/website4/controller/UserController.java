package website4.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Map.Entry;

import website4.database.DatabaseProvider;
import website4.database.IDatabase;
///import website4.database.DerbyDatabase.Transaction;
import website4.databsecontroler.InitDatabase;
import website4.model.usser;

public class UserController {
	
	private usser userModel = new usser();
	
	public UserController() {
	
		
	}
	
	public void setModel(usser userModel) {
		this.userModel = userModel;
	}
	
	/**
	 * this method will take in a user name and password and search the data base(for now an array)
	 * and return the the user id  associated with that user if the user exists, if no user with that name or 
	 * password is found it will throw no such element exception.
	 * 
	 * the user id will later be used by other methods to search the data base to find relevant information
	 * 
	 * 
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public usser loguserin(String username,String password) {
		usser retur = null;
		//retur.setusername(null);
		//retur.setpassword(null);
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		retur=db.loguserin(username, password);
		
			return retur;
		
		
	}
	/**
	 * intended to be used for session verification
	 * 
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public usser getuserbyid(int id) {
		/*
		for (int i=0;i<user.size()-1;i++) {
			if(user.get(i).getuserid()==id)
				return user.get(i);
			
		}
		*/
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getuser_by_id(id);
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		//String name="towerdef1";
		//Gson gson = new GsonBuilder().create();
		//String jsonchstpost = gson.toJson(addtouserscores(name,4,50));
		//System.out.println(jsonchstpost);
		//InitDatabase.init(1);
		//IDatabase db = DatabaseProvider.getInstance();
		//long now=Instant.now().toEpochMilli();
		//now+=86400000;
		//db.updateguestlist(now);
	}
	
	
	
	/**
	 * method cerates guest user and checks that their not in the database and then adds 
	 * the new guest to the database
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	
	public usser createguestuser() {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		int i=0;
		Integer id=null;
		long time = Instant.now().toEpochMilli();
		usser user = null;
		List<Entry<Integer, Long>> existing= db.getguestlist(); 
		for (Entry<Integer, Long> element : existing) {
		    // 1 - can call methods of element
			if(time-element.getValue()>86400000) {
				id=element.getKey();
				break;
			}
		    // !!!!!!!!! make guests reusable
		}
		while(i<100) {
			user=new usser();
			if (!db.checkdbcontainsuserid(user.getuserid())) {
				if (!db.checkdbcontainsusername(user.getusername())) {
					break;
					
					
				}
			
			}
			System.out.println("create guest user atempt #   "+i);
			i++;
		}
		db.addusertodb(user.getuserid(), user.getusername(), user.getpassword(), user.getemail(), user.getcoins());
		long now=Instant.now().toEpochMilli();
		db.addtoguestlist(user.getuserid(), now);
		
		return user;
	}
	
	
	
	public usser createUser(String userName, String password, String email) throws Exception {
		usser uzer = null;
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		if (userName != null && password != null && email != null) {
			boolean validInfo = isValid(userName, password, email);
			//PreparedStatement insertNewID = null;
			if (!db.checkdbcontainsusername(userName)) {
				if (validInfo == true) {
					int i=0;
					
					while(i<100) {
						
						uzer=new usser(userName,password,email);
						if (!db.checkdbcontainsuserid(uzer.getuserid())) {
								break;
						}
						System.out.println("create user atempt #   "+i);
						i++;
					}
					db.addusertodb(uzer.getuserid(), uzer.getusername(), uzer.getpassword(), uzer.getemail(), uzer.getcoins());
					
					return uzer;
					
					
				}
				
				
			}
			
			else {
				//send error to user that account already exists with user or email
				throw new Exception("An account with this username or email already exists.");
				
			}
			
		}
		else {
			//send error to user that information entered was not valid
			throw new Exception("The information entered is not valid.");
			
		}
		return uzer;
	}

	//Changes the user's password 
	public void modifyPassword(String password){
		if(password != null && checkPasswordLength(password) == true) {
			userModel.setpassword(password);
		}
	}
	
	//Changes the user's user name
	public void modifyUsername(String username) {
		if(username != null && checkUsernameLength(username) == true) {
			userModel.setusername(username);
		}
	}
	
	//Checks if length of password is between 6 and 20 characters
	//TODO:Display error message if too short or too long
	public boolean checkPasswordLength(String password) {
		if(password.length() < 6) {
			//password is too short
			return false;
		}
		else if(password.length() >= 6 && password.length() <= 20) {
			return true;
		}
		else {
			//password is too long
			return false;
		}	
	}
	//Checks if length of user name is between 6 and 20 characters
	//TODO:Display error message if too short or too long
	public boolean checkUsernameLength(String username) {
		if(username.length() < 6) {
			//user name is too short
			return false;
		}
		else if(username.length() >= 6 && username.length() <= 20) {
			return true;
		}
		else {
			//user name is too long
			return false;
		}	
	}
	

	
	// Checks that the user name and email do not already exist
		public boolean isValid(String userName, String password, String email) throws SQLException {
			
			if(userName != null && password != null && email != null && checkUsernameLength(userName) == true && checkPasswordLength(password) == true) {
				return true;
			}
			else {
				return false;
			}

		}
		
		/**
		 * use this method on any string input by the user
		 * 
		 * 
		 * 
		 * 
		 * @param toescape
		 * @return
		 */
		public String escapestring(String toescape){
			if(toescape!=null)
			for(int i=0;i<toescape.length();i++) {
				String v=String.valueOf( toescape.charAt(i));
				if(v.equals("&")) {
					String x="";
					if(i<toescape.length()-4) {
						x=toescape.substring(i, i+4);
					}
					String y="";
					if(i<toescape.length()-4) {
						y=toescape.substring(i, i+3);
					}
					if(x.equals("&amp;")||y.equals("&lt;")||y.equals("&gt;")) {
						
					}
					else {
						String lower=toescape.substring(0, i);
						String upper=toescape.substring(i+1, toescape.length());
						lower+="&amp;";
						toescape=lower+upper;
						i+=4;
					}
				}
				else if(v.equals("<")) {
					String lower=toescape.substring(0, i);
					String upper=toescape.substring(i+1, toescape.length());
					lower+="&lt;";
					toescape=lower+upper;
					i+=3;
				}
				else if(v.equals(">")) {
					String lower=toescape.substring(0, i);
					String upper=toescape.substring(i+1, toescape.length());
					lower+="&gt;";
					toescape=lower+upper;
					i+=3;
				}
				
			}
				
			
			return toescape;
		}

		public String un_escapestring(String tounescape){
			tounescape.replaceAll("&amp;", "&");
			tounescape.replaceAll("&lt;", "<");
			tounescape.replaceAll("&gt;", ">");
			
			
			return tounescape;
		}
		public boolean isguest(int userid) {
			
			InitDatabase.init(1);
			IDatabase db = DatabaseProvider.getInstance();
			return db.isguest(userid);
			
		}

		public void addimg(int userid,Blob img) {
			InitDatabase.init(1);
			IDatabase db = DatabaseProvider.getInstance();
			db.storeimg(userid, img);
			
		}
		
		public String getuserimg(int userid) {
			InitDatabase.init(1);
			IDatabase db = DatabaseProvider.getInstance();
			return db.getimg(userid);
		}


}



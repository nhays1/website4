package website4.controller;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;


import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.databsecontroler.InitDatabase;
import website4.model.usser;

public class UserController {
	
	private Connection conn;
	private ArrayList<usser> user =new ArrayList<usser>();
	private usser userModel = new usser();
	
	public UserController() {
		//userModel = User;
		usser user1;
		user1=new usser("placeholder","skanfurnsgiemdtenale023n!",129814,"none",0);
		user.add(user1);
		user1=new usser("jake","123",2,"notMyEmail@hotmail.gov",9999999);
		user.add(user1);
		user1=new usser("jake1","1234",222,"none",0);
		user.add(user1);
		user1=new usser("jake2","12345",333,"none",0);
		user.add(user1);
		user1=new usser("jake3","123456",444,"none",0);
		user.add(user1);
		user1=new usser("jason","1111",99999,"jasonp@json.net",-554);
		user.add(user1);
		
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
		
		for(int i=0;i<user.size();i++) {
			if(user.get(i).getusername().equals(username)&&user.get(i).getpassword().equals(password)) {
				retur=user.get(i);
				break;			}
		}
		
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
	
	
	public void createUser(String userName, String password, String email) throws SQLException {
		if (userName != null && password != null && email != null) {
			boolean validInfo = isValid(userName, password, email);
			//PreparedStatement insertNewID = null;

			if (validInfo == true) {
				// Add information to database
				
				//After information is tested to be valid, add a new user to the array list
				user.add(new usser(userName, password, email));
				
			
				
				//Finish this implementation after we have a database	
				
				//Inserts new Username and Password into database with a new ID

//				String newID = "insert into userInfo(userName, password) values (?, ?)";
//				insertNewID = conn.prepareStatement(newID);
//				insertNewID.setString(1, userName);
//				insertNewID.setString(2, password);
//				insertNewID.execute();

				// user.setusername(userName);
				// user.setpassword(password);
				// user.setemail(email);
				// user.setcoins(1000);

			}
			
			else {
				//send error to user that account already exists with user or email
				
			}
			
		}
		else {
			//send error to user that information entered was not valid
		}
	}

	//Changes the user's password 
	public void modifyPassword(String password){
		//if(password != null && checkPasswordLength(password) == true) {
			userModel.setpassword(password);
		//}
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
			
			
			
			
			
//			conn = null;
//			PreparedStatement stmt = null;
//			ResultSet resultSet = null;
				
/**			
			try {

				stmt = conn.prepareStatement("select username, email from userInfo where userName = ? and email = ?");
				stmt.setString(1, userName);
				stmt.setString(2, email);
				//////////////////////////////////////////////////////
				resultSet = stmt.executeQuery();
				ResultSetMetaData resultSchema = stmt.getMetaData();
				int rowsReturned = 0;

				while (resultSet.next()) {
						// startBlock: this block will probably be removed later
					for (int i = 1; i <= resultSchema.getColumnCount(); i++) {
						Object obj = resultSet.getObject(i);
						if (i > 1) {
							System.out.print(",");
						}
						System.out.print(obj.toString());
						// endBlock
					}
					// System.out.println();
					// count # of rows returned
					rowsReturned++;
				}
				// indicate if the query returned nothing
				if (rowsReturned == 0) {
					return true;
				}
			} // end of try block
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return false;
		}
	
	
		public String logUserIn(String username,String password) {
			String retur = null;
			for(int i=0;i<user.size()-1;i++) {
				if(user.get(i).getusername()==username||user.get(i).getpassword()==password) {
					retur=user.get(i).getusername();
					break;
				}
			}
			if(retur!=null)
				return retur;
			else 
				throw new NoSuchElementException();
		}
**/
}








}























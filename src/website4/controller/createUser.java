package website4.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

import website4.model.usser;


// TODO this controller will take user name and password from servlet and check
// or reject the information
// if the information is invalid it should throw an exception to the servlet
// which will send the message to the user
public class createUser {

	// Map<Integer, String> userMap = new HashMap<Integer, String>();

	

	// This information will come from the servlet, these are just temporary
	// variables
	//private String userName;
	//private String password;
	//private String email;
	// private boolean isUserGuest = true;

	private Connection conn;


	
	public createUser() {
		
	}
	
	public void createNewUser(String userName, String password, String email) throws SQLException {
		
		
		
		if (userName != null && password != null && email != null) {
			boolean validInfo = isValid(userName, password, email);
			//PreparedStatement insertNewID = null;

			if (validInfo == true) {
				// Add information to database
				
				//After information is tested to be valid, create a new user and then set it's attributes
				usser user = new usser();
				
				user.setusername(userName);
				user.setpassword(password);
				user.setemail(email);
				
				
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

	// Checks that the username and email do not already exist
	public boolean isValid(String userName, String password, String email) throws SQLException {
		conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
			
		
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
}

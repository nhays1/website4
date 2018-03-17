package website4.controller;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.ycp.cs320.sqldemo.DBUtil;
import website4.model.usser;

public class createUser {
	
	
	
	
	//Map<Integer, String> userMap = new HashMap<Integer, String>();
	
	//private usser user = new usser();
	
	//This information will come from the servlet, these are just temporary variables
	private String userName = "name";
	private String password = "pass";
	private String email = "email";
	//private boolean isUserGuest = true;

	private Connection conn;
	
	
	createUser() throws SQLException{
		if(userName != null && password != null && email != null) {
			boolean validInfo = isValid(userName, password, email);
			
			
			if(validInfo == true) {
			//Add information to database
				
				
				
//				user.setusername(userName);
//				user.setpassword(password);		
//				user.setemail(email);	
//				user.setcoins(1000);
				
			}
		}
	}
	
	//Checks that the username and email do not already exist
	public boolean isValid(String userName, String password, String email) throws SQLException {
		
		conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
		stmt = conn.prepareStatement(
				 "select username, email from userInfo where userName = ? and email = ?"	
		);

		stmt.setString(1, userName);
		stmt.setString(2, email);
		}
		
		
		
		
		
		
		
		
		
		
		catch(Exception e) {
		
		System.out.println(e.getMessage());
		
		}
		
		
		
		return false;
	}
	
	
	//TODO this controler will take username and password from servlet and check or reject the information
	// if the information is invalid it should throw an exception to the servlet which will send the message to the user
	
	
	
	
	
	
	
	
	
	
	
}

package website4.controller;



import java.util.ArrayList;
import java.util.NoSuchElementException;

import website4.model.usser;

public class loginverification {
	
	private ArrayList<usser> ussrs=new ArrayList<usser>();
	
	//TODO this controler will take username and password from servlet and check or reject the information
	// if the information is invalid it should throw an exception to the servlet which will send the message to the user\
	//
	//im not sure wether we will store the user object directly in the database or just store the user data then create a new user object with
	// that information uppon log in     our choice on this isue will heavly affect this class
	//however we can still write the code that comunictaes with the servlet and verifies the information/ throws exceptions
	
	public loginverification() {
		usser user;
		user=new usser("jake","123");
		ussrs.add(user);
		user=new usser("jake1","1234");
		ussrs.add(user);
		user=new usser("jake2","12345");
		ussrs.add(user);
		user=new usser("jake3","123456");
		ussrs.add(user);
		
		
	}
	/**
	 * this mithod will take in a user name and password and search the data base(for now an array)
	 * and return the the user id  ascocated with that user if the user exists, if no user with that name or 
	 * password is found it will throw no such element excetion.
	 * 
	 * the user id will later be used by other methods to search the data base to find relavent information
	 * 
	 * 
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public String loguserin(String username,String password) {
		String retur = null;
		for(int i=0;i<ussrs.size()-1;i++) {
			if(ussrs.get(i).getusername()==username||ussrs.get(i).getpassword()==password) {
				retur=ussrs.get(i).getusername();
				break;
			}
			

		}
		if(retur!=null)
			return retur;
		else 
			throw new NoSuchElementException();
		
	}
	
	
	
	
	
	
	
	
}

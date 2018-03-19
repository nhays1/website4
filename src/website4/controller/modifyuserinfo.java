package website4.controller;



import website4.model.usser;

public class modifyuserinfo {
	private usser user;
	
	public void setModel(usser user) {
		
		
		this.user = user;
	}
	//TODO this controler will comunicate with the modifyuser servlit and verify that user input is valid befor changing the user object
	// if the information is invalid it should throw an exception to the servlet which will send the message to the user\
	//
	//similar caveat about the database structure    however for this class id imagine we could prety easyly just modify the user 
	// object directly
	
	
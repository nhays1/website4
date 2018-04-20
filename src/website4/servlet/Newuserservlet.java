package website4.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import edu.ycp.cs320.lab02.controller.AddNumbersController;
import website4.controller.UserController;
import website4.database.DerbyDatabase;
import website4.model.usser;

public class Newuserservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean success;
	private boolean validUser;
	private boolean validPass;
	private boolean validEmail;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("__________________________________________________________");
		System.out.println("newuser Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/newuser.jsp").forward(req, resp);
		success = false;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("_______________________________________________");
		System.out.println("newuser Servlet: doPost"); 
		
		// holds the error message text, if there is any
		String errorMessage = null; 
		String userErrorMessage = null;
		String passErrorMessage = null;
		String emailErrorMessage = null;
		success = false;
		
		// decode POSTed form parameters and dispatch to controller
	
			
			UserController controller = new UserController();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String rpass = req.getParameter("rpass");
			String remail = req.getParameter("remail");
			System.out.println("_______________________________________________");
			System.out.println("Username: "+username);
			System.out.println("Password: "+password);
			System.out.println("Email: "+email);
			System.out.println("Rpass: "+rpass);
			System.out.println("Remail: "+remail);
			System.out.println("_______________________________________________");
			usser user = new usser(null, null);
			
			try {


			//Check username for validness
			if(username == null || username == "") {
				userErrorMessage = "Please enter a valid username";
				validUser = false;
			}
			
			else if(controller.checkUsernameLength(username) == false) {
				userErrorMessage = "Username must be between 6-20 characters";
				validUser = false;
			}			
			else {
				System.out.println("validUser = true");
				userErrorMessage = null;
				validUser = true;
			}			
			
			//Check password for validness
			if(password == null || password == "") {
				passErrorMessage = "Please enter a valid password";
				validPass = false;
			}
			else if(controller.checkPasswordLength(password) == false) {
				passErrorMessage = "Password must be between 6-20 characters";
				validPass = false;
			}
			else if(password.equals(rpass) == false) {
				passErrorMessage = "Passwords do not match";
				validPass = false;
			}
			else {
				System.out.println("validPass = true");
				passErrorMessage = null;
				validPass = true;
			}			
						
			//Check email for validness
			if(email == null || email == "") {
				emailErrorMessage = "Please enter a valid email";
				validEmail = false;
			}
			else if(email.equals(remail) == false) {
				emailErrorMessage = "Emails do not match";
				validEmail = false;
			}
			else {
				emailErrorMessage = null;
				System.out.println("validEmail = true");
				validEmail = true;
			}
				
				
			if(validUser == true && validPass == true && validEmail == true) {
				success = true;
				user = controller.createUser(username, password, email);
				System.out.println("User created successfully");
				Gson gson = new GsonBuilder().create();
				System.out.println(gson.toJson(user));
				req.getSession().setAttribute("userid", user.getuserid());
				controller.loguserin(username, password);
			}					
				
						
				//req.getSession().setAttribute("userid", user.getuserid());
				//controller.loguserin(username, password);
				
		}
			
			catch (SQLException e) {
			
			errorMessage = e.getMessage();
		} catch (Exception e) {
				
			errorMessage = e.getMessage();
			} 
		
	
		
			
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("rpass", req.getParameter("rpass"));
		req.setAttribute("remail", req.getParameter("remail"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("userErrorMessage", userErrorMessage);
		req.setAttribute("passErrorMessage", passErrorMessage);
		req.setAttribute("emailErrorMessage", emailErrorMessage);
		
		
		// Forward to view to render the result HTML document 
		//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		req.getSession().setAttribute("userid", user.getuserid());
		
		if(success == true) {//Sends user back to index page
			controller.loguserin(username, password);
			req.getRequestDispatcher("/_view/userinfo.jsp").forward(req, resp);
		}
		else {
			errorMessage = "Something went wrong";
			req.getRequestDispatcher("/_view/newuser.jsp").forward(req, resp);
		}
	}
}

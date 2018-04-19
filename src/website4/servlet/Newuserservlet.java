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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("__________________________________________________________");
		System.out.println("newuser Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/newuser.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("_______________________________________________");
		System.out.println("newuser Servlet: doPost");
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		// decode POSTed form parameters and dispatch to controller
	
			
			UserController controller = new UserController();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			System.out.println(username);
			System.out.println(password);
			System.out.println(email);
			usser user = new usser(null, null);
			
			try {
			// check for errors in the form data before using is in a calculation
			if (username == null || password == null || email == null || email == "") { //checks all inputs are not null
				
				errorMessage = "Username, Password, or Email is typed incorrectly";	
			}
			else if(controller.checkUsernameLength(username) == false) {//checks users name length
				
				errorMessage = "Username is either too long or too short";
			}
			else if(controller.checkUsernameLength(username) == false && controller.checkPasswordLength(password) == false) {
				errorMessage = "Username and password are too long or too short";
			}
			else if(controller.checkPasswordLength(password) == false) {//check password length
				
				errorMessage = "Password is either too long or too short";
			}
			else {//successfully creates user
				
				success = true;
				user = controller.createUser(username, password, email);
				System.out.println("User created successfully");
				Gson gson = new GsonBuilder().create();
				System.out.println(gson.toJson(user));
				
				//req.getSession().setAttribute("userid", user.getuserid());
				//controller.loguserin(username, password);
				
				
				
			}
		}
			
			catch (SQLException e) {
			
			errorMessage = e.getMessage();
		} catch (Exception e) {
				
			errorMessage = e.getMessage();
			} 
		
	
		
			
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("email", req.getParameter("email"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		
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

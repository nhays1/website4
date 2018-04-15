package website4.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import edu.ycp.cs320.lab02.controller.AddNumbersController;
import website4.controller.UserController;
import website4.database.DerbyDatabase;

public class Newuserservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean success;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("newuser Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/newuser.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("newuser Servlet: doPost");
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			System.out.println("Inside Try");//for testing purposes
			UserController controller = new UserController();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");

			// check for errors in the form data before using is in a calculation
			if (username == null || password == null || email == null) { //checks all inputs are not null
				System.out.println("if1");//for testing purposes
				errorMessage = "Username, Password, or Email is typed incorrectly";	
			}
			else if(controller.checkUsernameLength(username) == false) {//checks usersname length
				System.out.println("if2");//for testing purposes
				errorMessage = "Username is either too long or too short";
			}
			else if(controller.checkPasswordLength(password) == false) {//check password length
				System.out.println("if3");//for testing purposes
				errorMessage = "Password is either too long or too short";
			}
			else {//successfully creates user
				System.out.println("else");//for testing purposes
				success = true;
				DerbyDatabase db = new DerbyDatabase();
				db.createUser(username, password, email);
			}
		} catch (SQLException e) {
			System.out.println("catch");//for testing purposes
			errorMessage = "Invalid Input";
		} 
		
	
		
		
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("email", req.getParameter("email"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("end");//for testing purposes
		
		// Forward to view to render the result HTML document 
		//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		
		if(success == true) {//Sends user back to index page
			req.getRequestDispatcher("/_view/userinfo.jsp").forward(req, resp);
		}
		else {
			errorMessage = "Something went wrong";
			req.getRequestDispatcher("/_view/newuser.jsp").forward(req, resp);
		}
	}
}

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
			UserController controller = new UserController();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");

			// check for errors in the form data before using is in a calculation
			if (username == null || password == null || email == null) {
				errorMessage = "Username, Password, or Email is typed incorrectly";	
			}
			else if(controller.checkUsernameLength(username) == false) {
				errorMessage = "Username is either too long or too short";
			}
			else if(controller.checkPasswordLength(password) == false) {
				errorMessage = "Password is either too long or too short";
			}
			else {
				DerbyDatabase db = new DerbyDatabase();
				db.createUser(username, password, email);
			}
		} catch (SQLException e) {
			errorMessage = "Invalid Input";
		} 
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("email", req.getParameter("email"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document 
		req.getRequestDispatcher("/_view/newuser.jsp").forward(req, resp);
		
		
	}
}

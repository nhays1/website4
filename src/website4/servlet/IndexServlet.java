package website4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import website4.controller.IndexController;



public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String result;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("index Servlet: doPost");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		
		errorMessage = null;
		result = null;
	
	//START CHAT
		try {
			String text = req.getParameter("chatinputtext");
			
		//checks if there was any data entered
			if (text == null) {
				errorMessage = "Please enter a valid submission.";
			}
			
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				//Need to create a controller and then store the data somewhere
			
			
			//IndexController controller = new IndexController();
			//controller.postChat(text);

			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Submission";
		}
		
		//Add parameter as request attribute
		req.setAttribute("chatinputtext", req.getParameter("chatinputtext"));
		
		//Add result object as attribute
		req.setAttribute("result", result);
		req.setAttribute("errorMessage", errorMessage); //this needs to be added to the jsp
		
		//Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
		
		
		
		//Comment made for testing
		
		
		
		
	}//End of doPost//
}

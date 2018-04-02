package website4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import website4.controller.CoinGame;



public class Gamewindowservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Gamewindow Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/Gamewindow.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Gamewindow Servlet: doPost");
		
		CoinGame cg = new CoinGame();
		
		
		// holds the error message text, if there is any
		
	
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Gamewindow.jsp").forward(req, resp);
	}

	
	
}

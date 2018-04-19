package website4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Cards.CardDeck;



public class Game2windowservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Gamewindow Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/Game2window.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Gamewindow 2 Servlet: doPost");
		
		CardDeck cd1 = new CardDeck();
		cd1.createDeck();
		CardDeck cd2 = new CardDeck(cd1.splitDeck(cd1.getDeck()));
		int userBet = 0;
		int reward = 0;
		
		
		// holds the error message text, if there is any
		
	
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Game2window.jsp").forward(req, resp);
	}

	
	
}

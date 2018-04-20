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
		int userSelection = 0;
		
		String result = null;
		String userCard = " ";
		String cpuCard = " ";
		String errorMessage;
		String choice = null;
		
		try {
			
			userBet = getInteger(req, "userBet");
			
			try {
				
				if (req.getParameter(choice) != null) {
					
					choice = getChoice(req, "choice");
					
				}
			}catch(NullPointerException e) {
				errorMessage = "Please select an option";
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Input";
		}
		
		if(choice.equals("higher")) {
			userSelection = 1;			//higher is 1
		}
		else {
			userSelection = 0;			//lower is 0
		}
		
		// check for errors in the form data before using is in a calculation
					if (userBet <= 0) {
						errorMessage = "Please enter a valid Bet Amount";
					}
					// otherwise, data is good, do the calculation
					// must create the controller each time, since it doesn't persist between POSTs
					// the view does not alter data, only controller methods should be used for that
					// thus, always call a controller method to operate on the data
					
					if(req.getParameter("higher") != null) {
						choice = req.getParameter("higher");
						
					}
					
					else {
						cd1.setSelection(userSelection);
						cd1.setBet(userBet);
						
						if(cd1.isWin(cd1.getTopCard(), cd2.getTopCard()) == 1) {
							reward = userBet * 3;
							result = "You have won the card guess!";
							
							//currentUser.setcoins(currentUser.getcoins() + reward);
							//transactMsg = transactMsg.concat("User " + currentUser.getusername() + " has won " + reward + " Coins!");
						}
						else {
							result = "You have lost the card guess...";
							reward = 0 - userBet;
							//currentUser.setcoins(currentUser.getcoins() + reward);
							//transactMsg = transactMsg.concat("User " + currentUser.getusername() + " has lost " + userBet + " Coins!");
						}
					}
		
		// holds the error message text, if there is any
		errorMessage = null;
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Game2window.jsp").forward(req, resp);
	}
	
	
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	private String getChoice(HttpServletRequest req, String choice) {
		return req.getParameter(choice);
	}
}

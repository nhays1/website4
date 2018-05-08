package website4.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Cards.Card;
import Cards.CardDeck;
import website4.controller.UserController;
import website4.model.usser;



public class Game2windowservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Gamewindow  2 Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/Game2window.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Gamewindow 2 Servlet: doPost");
		
		usser currentUser = null;
		UserController uc = new UserController();
		
        Integer userid = (Integer) req.getSession().getAttribute("userid");
        if(userid!=null) {
           
            currentUser=uc.getuserbyid(userid);

        }
        
        if(currentUser==null) {//if user id was not found creates a new guest 
            currentUser= uc.createguestuser() ;
        }
		
		uc.getuserbyid(currentUser.getuserid());
		
		
		CardDeck userDeck = new CardDeck();
		userDeck.createDeck();
		userDeck.shuffleDeck();
		CardDeck cpuDeck = new CardDeck(userDeck.splitDeck(userDeck.getDeck()));
		Card userCard;
		Card cpuCard;
		
		int cardIndex;
		int userBet = 0;
		int reward = 0;
		
		String result = " ";
		String errorMessage = " ";
		String choice = " ";
		String transactMsg = "";
		
		try {
			userBet = getInteger(req, "userBet");
			try {
				
				if (req.getParameter(choice) != null) {
					choice = getChoice(req, "choice");
				}
				
			}catch(NullPointerException e) {
				errorMessage = "Please select an option";
			}
			

			if (userBet <= 0) {
				errorMessage = "Please enter a valid Bet Amount";
			}
			
			else {
				//calculations with decks and cards, and comparisons
				reward = userBet * 3;
			}
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Input";
		}
		
		req.setAttribute("userBet", req.getParameter("userBet"));
		
		if(userDeck.pullCard().getRankToCompare() > cpuDeck.pullCard().getRankToCompare()) {
			result = "You have won the card game!";
			currentUser.setcoins(currentUser.getcoins() + reward);
			transactMsg = transactMsg.concat(currentUser.getusername() + " has won " + reward + " Coins!");
			
			//add user updates here
		}
		else {
			result = "You have lost the card game...";
			reward = 0 - userBet;
			currentUser.setcoins(currentUser.getcoins() + reward);
			transactMsg = transactMsg.concat(currentUser.getusername() + " has lost " + userBet + " Coins!");
			//add user updates here
		}
		
		// Forward to view to render the result HTML document
		req.getSession().setAttribute("userid", currentUser.getuserid());
		req.setAttribute("userCardResult", userDeck.getTopCard().toString());
		req.setAttribute("userCardIndex", userDeck.pullCard().getCardIndex());
		req.setAttribute("cpuCardIndex", cpuDeck.pullCard().getCardIndex());
		req.setAttribute("cpuCardResult", cpuDeck.getTopCard().toString());
		req.setAttribute("transactMsg", transactMsg);
		req.getRequestDispatcher("/_view/Game2window.jsp").forward(req, resp);
		System.out.println(errorMessage);
	}
	
	
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	private String getChoice(HttpServletRequest req, String choice) {
		return req.getParameter(choice);
	}
}

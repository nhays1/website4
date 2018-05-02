package website4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import website4.controller.CoinGame;
import website4.controller.UserController;
import website4.model.usser;



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
		
		CoinGame cg = new CoinGame();
		String choice = " ";
		int userBet = 0;
		int reward = 0;
		int coinFlip = 0;
		boolean isWin = false;
		String result = " ";
		int userSelection = 0;
		String transactMsg = "";
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		try {
			
			userBet = getInteger(req, "userBet");
			
			
			try {
				
				if (req.getParameter(choice) != null) {
					
					choice = getChoice(req, "choice");
					
				}
				
			}catch(NullPointerException e) {
				
				errorMessage = "Please select an option";
				
			}
			
			if(choice.equals("heads")) {
				userSelection = 0;
			}
			else {
				userSelection = 1;
			}
			
			
			
			// check for errors in the form data before using is in a calculation
			if (userBet <= 0) {
				errorMessage = "Please enter a valid Bet Amount";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				cg.flip();
				cg.setSelection(userSelection);
				cg.setBet(userBet);
				coinFlip = cg.getFlip();
				isWin = cg.getIsWin();
				reward = cg.getReward();
				
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Input";
		}
		
		req.setAttribute("userBet", req.getParameter("userBet"));
		
		if(isWin) {
			result = "You have won the coin toss vs. CPU!";
			currentUser.setcoins(currentUser.getcoins() + reward);
			transactMsg = transactMsg.concat("User " + currentUser.getusername() + " has won " + reward + " Coins!");
			
			//add user updates here
		}
		
		else {
			result = "You have lost the coin toss vs. CPU...";
			reward = 0 - userBet;
			currentUser.setcoins(currentUser.getcoins() + reward);
			transactMsg = transactMsg.concat("User " + currentUser.getusername() + " has lost " + userBet + " Coins!");
			//add user updates here
		}
		
		req.setAttribute("coinFlip", coinFlip);
        req.getSession().setAttribute("userid", currentUser.getuserid());
        req.setAttribute("choice", choice);
		req.setAttribute("transaction", transactMsg);
		req.setAttribute("result", result);
		req.getRequestDispatcher("/_view/Gamewindow.jsp").forward(req, resp);
		System.out.println(errorMessage);
	}

	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	private String getChoice(HttpServletRequest req, String choice) {
		return req.getParameter(choice);
	}
	
}

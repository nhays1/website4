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
		
		usser currentUser = new usser();
		UserController uc = new UserController();
		
		uc.getuserbyid(currentUser.getuserid());
		
		System.out.println("Gamewindow Servlet: doPost");
		CoinGame cg = new CoinGame();
		String choice;
		int userBet = 0;
		int reward = 0;
		boolean isWin = false;
		String result;
		int userSelection = 0;

		// holds the error message text, if there is any
		String errorMessage = null;
		
		try {
			
			userBet = getInteger(req, "userBet");
			try{
				choice = getChoice(req, "choice");
			}catch(NullPointerException e) {
				errorMessage = "choice is null";
			}
			//if(choice == "heads") {
			//	userSelection = 1;
			//}
			//else {
			//	userSelection = 0;
			//}
			
			
			
			// check for errors in the form data before using is in a calculation
			if (userBet <= 0) {
				errorMessage = "Please enter a valid Bet Amount";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				cg.setSelection(userSelection);
				cg.setBet(userBet);
				cg.flip();
				isWin = cg.getIsWin();
				reward = cg.getReward();
				
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Input";
		}
		
		req.setAttribute("userBet", req.getParameter("userBet"));
		
		if(isWin) {
			result = "User Has Guessed the flip correctly!";
			
		}
		req.setAttribute("result", isWin);
		req.getRequestDispatcher("/_view/Gamewindow.jsp").forward(req, resp);
	}

	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	private String getChoice(HttpServletRequest req, String choice) {
		return req.getParameter(choice);
	}
	
}

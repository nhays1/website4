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
		int userBet = 0;
		int reward = 0;
		int coinFlip = 0;
		boolean isWin = false;
		String result = " ";
		int userSelection = 0;
		String transactMsg = "";
		
		// holds the error message text, if there is any
		String errorMessage = " ";
		String choice = null;
		
		try {
			
			userBet = getInteger(req, "userBet");
			
			try {
				choice = req.getParameter("choice");
			}catch(NullPointerException e) {
				errorMessage = "Please choose an option";
				choice = "tails";
			}
			
			try {
				
				if(choice.equals("heads")) {
					userSelection = 1;
				}
				else{
					userSelection = 0;
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
					req.setAttribute("coinFlip", coinFlip);
					System.out.println("user chose " + cg.getSelection());
					System.out.println("coin flipped as " + cg.getFlip());
					isWin = cg.getIsWin();
					reward = cg.getReward();
					
				}
			} catch (NumberFormatException e) {
				errorMessage = "Invalid Input";
			}
			
			req.setAttribute("userBet", req.getParameter("userBet"));
			
			//if(userBet > currentUser.getcoins()) {
			//	errorMessage = "User does not have enough coins";
			//}
			//else {
				if(isWin) {
					result = "You have won the coin toss!";
					currentUser.setcoins(currentUser.getcoins() + reward);
					transactMsg = transactMsg.concat(currentUser.getusername() + " has won " + reward + " Coins!");
					if (cg.getSelection() == 1) {
		
						System.out.println("user chose heads");
						
						if(cg.getFlip() == 1) {
							System.out.println("coin flipped as heads");
						}
						else {
							System.out.println("coin flipped as tails");
						}
						
					}
					else {
						
						System.out.println("user chose tails");
						
						if(cg.getFlip() == 1) {
							System.out.println("coin flipped as heads");
						}
						else {
							System.out.println("coin flipped as tails");
						}
					}
					
					System.out.println(" user should have won here");
					//add user updates here
				}
				
				
				
				else {
					result = "You have lost the coin toss...";
					reward = 0 - userBet;
					currentUser.setcoins(currentUser.getcoins() + reward);
					transactMsg = transactMsg.concat(currentUser.getusername() + " has lost " + userBet + " Coins!");
					if (cg.getSelection() == 1) {
		
						System.out.println("user chose heads");
						
						if(cg.getFlip() == 1) {
							System.out.println("coin flipped as heads");
						}
						else {
							System.out.println("coin flipped as tails");
						}
						
					}
					else {
						
						System.out.println("user chose tails");
						
						if(cg.getFlip() == 1) {
							System.out.println("coin flipped as heads");
						}
						else {
							System.out.println("coin flipped as tails");
						}
					}
					System.out.println(" user should have lost here");
					//add user updates here
				}
			//}
	        req.getSession().setAttribute("userid", currentUser.getuserid());
	        req.setAttribute("errorMessage", errorMessage);
	        req.setAttribute("choice", choice);
			req.setAttribute("transaction", transactMsg);
			req.setAttribute("result", result);
			req.getRequestDispatcher("/_view/Gamewindow.jsp").forward(req, resp);
			System.out.println(errorMessage);
		}catch(NullPointerException e) {
			errorMessage = "please choose an option";
			userSelection = 0;
			userBet = 0;
			transactMsg = "nothing happened you dunce";
			result = " ";
			
			
			req.getSession().setAttribute("userid", currentUser.getuserid());
	        req.setAttribute("errorMessage", errorMessage);
	        req.setAttribute("choice", choice);
			req.setAttribute("transaction", transactMsg);
			req.setAttribute("result", result);
			req.getRequestDispatcher("/_view/Gamewindow.jsp").forward(req, resp);
		}
	}
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
	
}

package website4.servlet;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import website4.controller.chatcontroler;
import website4.controller.loginverification;
import website4.model.post;
import website4.model.usser;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String result;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("index Servlet: doGet");
		int chatlength;
		chatcontroler chat =new chatcontroler();
		ArrayList<post> chatposts;
		chatposts= (ArrayList<post>) chat.Getchat();
		usser user=new usser();
		
		
		chatlength=chatposts.size();
		
		Gson gson = new GsonBuilder().create();
		String jsonchstpost = gson.toJson(chatposts);
		
		//System.out.println("jsonobj      _"+jsonchstpost);
		
		req.setAttribute("user", user);
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("index Servlet: doPost");
		int chatlength;
		
		chatcontroler chat =new chatcontroler();
		usser user=new usser();
		
		System.out.println("__________________________________________________________");
	
		
		
		
		
		try {
			
			String ussing =  req.getParameter("usreing");
			
			if(ussing!=null) {
				if(ussing!="") {
					user.setusername(ussing);
				}

				
			}
			
			String chatinput =  req.getParameter("chatinputtext");
			String username =  req.getParameter("username");
			String password =  req.getParameter("password");
			
			
			
			System.out.println("ussing      _ "+ussing);
			
			System.out.println("username      _ "+username);
			System.out.println("assword      _ "+password);
			System.out.println("chat      _ "+chatinput);
			
			if(username!=null&&password!=null) {
				if(username.trim().length()>0&&password.trim().length()>0) {
					loginverification loger=new loginverification();
						usser temp=loger.loguserin(username, password);
						
					if(temp!=null) {
						user=temp;
					}
				}
			}
			
			if(chatinput!=null) {
				if(chatinput.trim().length()>0) {
					long now=Instant.now().toEpochMilli();
					//if()
					chat.makenewpost(now, user.getusername(), chatinput);
					System.out.println("chatpassed      _ "+chatinput);
					System.out.println("chatpasseduser  _ "+user.getusername());
					ArrayList<post> chatposts= (ArrayList<post>) chat.Getchat();
					
					System.out.println("chatpassed      _ "+chatposts.get(chatposts.size()-1));
					
					
					
				}
			}
			
			

		}
		finally{
			
		}
		
		

		
		ArrayList<post> chatposts;
		chatposts= (ArrayList<post>) chat.Getchat();
		
		chatlength=chatposts.size();
		
		Gson gson = new GsonBuilder().create();
		String jsonchstpost = gson.toJson(chatposts);
		
		System.out.println("jsonobj      _"+jsonchstpost);
		System.out.println("username      _ "+user.getusername());
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		
		//errorMessage = null;
		//result = null;
	
	//START CHAT
		/*
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
		
		
		*/
		
	}//End of doPost//
	
	
	
	

}

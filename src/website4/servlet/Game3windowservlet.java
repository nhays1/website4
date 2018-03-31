package website4.servlet;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import website4.controller.UserController;
import website4.controller.chatcontroler;
import website4.model.post;
import website4.model.usser;



public class Game3windowservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Gamewindow Servlet: doGet");	
		
		System.out.println("__________________________________________________________");
		System.out.println("index Servlet: DOGET");
		System.out.println("__________________________________________________________");
		int chatlength;
		chatcontroler chat =new chatcontroler();
		UserController usecontrol=new UserController();
		
		
		ArrayList<post> chatposts;
		chatposts= (ArrayList<post>) chat.Getchat(0);
		
		//
		usser user = null;
		Integer userid = (Integer) req.getSession().getAttribute("userid");
		if(userid!=null) {
		
			user=usecontrol.getuserbyid(userid);
			
		}
	
		if(user==null) {//if user id was not found creates a new guest 
			user=usecontrol.createguestuser();
			
		}
		req.getSession().setAttribute("userid", user.getuserid());
		//
		
		chatlength=chatposts.size();
		
		Gson gson = new GsonBuilder().create();
		String jsonchstpost = gson.toJson(chatposts);
		
		//System.out.println("jsonobj      _"+jsonchstpost);
		
		
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/Game3window.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Gamewindow Servlet: doPost");
		
		
		
		System.out.println("__________________________________________________________");
		System.out.println("index Servlet: doPost");
		int chatlength;
		Integer numpost = 0 ;
		boolean logout=false;
		
		int score = 0;
		
		chatcontroler chat =new chatcontroler();
		UserController usecontrol=new UserController();
		
		
	
		
		//
		usser user = null;
		Integer userid = (Integer) req.getSession().getAttribute("userid");
		if(userid!=null) {
			
			user=usecontrol.getuserbyid(userid);
			System.out.println("sesion usser     "+user.getusername());
		}
	
		if(user==null) {//if user id was not found creates a new guest 
			user=usecontrol.createguestuser() ;

		}
		//req.getSession().setAttribute("userid", userid);
		//
		
		
		try {
			
			
			String skore= req.getParameter("score");
			if(skore!=null)
				score =Integer.parseInt(skore);
			
			System.out.println("score      _"+score);
			
			/*
			String chatinput =  req.getParameter("chatinputtext");
			String username =  req.getParameter("username");
			String password =  req.getParameter("password");
			String numberofposts = req.getParameter("numberofpost");
			if(req.getParameter("logout")!=null)
				logout= Boolean.parseBoolean( req.getParameter("logout"));
			
			if(numberofposts!=null&&Integer.parseInt(numberofposts)!=0)
				 numpost =Integer.parseInt(numberofposts);
			
			System.out.println("wants logout   "+logout);
			
			if(logout) {
				user=usecontrol.createguestuser();
			
				
				
			}
			*/
			/*
			System.out.println("number of posts   "+numpost);
		
			
			System.out.println("username      _ "+username);
			System.out.println("assword      _ "+password);
			System.out.println("chat      _ "+chatinput);
			
			if(username!=null&&password!=null) {
				if(username.trim().length()>0&&password.trim().length()>0) {
					
						usser temp=usecontrol.loguserin(username, password);
						
					if(temp!=null) {
						user=temp;
					}
				}
			}
			
			if(chatinput!=null) {
				if(chatinput.trim().length()>0) {
					long now=Instant.now().toEpochMilli();
					//if()
					chat.makenewpost(now,user.getuserid() , chatinput);
					
					
					
					System.out.println("chatpassed      _ "+chatinput);
					System.out.println("chatpasseduser  _ "+user.getusername());
					//ArrayList<post> chatposts= (ArrayList<post>) chat.Getchat(0);
					
					//System.out.println("chatpassed      _ "+chatposts.get(chatposts.size()-1).Getpost());
					
					
					
				}
			}
			
			*/

		}
		finally{
			
		}
		
		

		
		ArrayList<post> chatposts;
		System.out.println("numpostsssss      _ "+numpost);
		chatposts= (ArrayList<post>) chat.Getchat(numpost);
		System.out.println("numpostdddddd      _ "+chatposts.size());
		
		
		
		chatlength=chatposts.size();
		
		Gson gson = new GsonBuilder().create();
		String jsonchstpost = gson.toJson(chatposts);
		
		req.getSession().setAttribute("userid", user.getuserid());
		
		//System.out.println("jsonobj      _"+jsonchstpost);
		System.out.println("username      _ "+user.getusername());
		//req.logout();
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		req.setAttribute("user", user);
		
		

		
	
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Game3window.jsp").forward(req, resp);
	}

	
	
}

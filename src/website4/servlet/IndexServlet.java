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

import website4.controller.UserController;
import website4.controller.chatcontroler;

import website4.model.post;
import website4.model.usser;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String result;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("__________________________________________________________");
		System.out.println("index Servlet: doPost");
		int chatlength;
		Integer numpost = 0 ;
		boolean logout=false,morepots=false;
		boolean sync=true;
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

			String chatinput =  req.getParameter("chatinputtext");
			String username =  req.getParameter("username");
			String password =  req.getParameter("password");
			String numberofposts = req.getParameter("numberofpost");
			String moreposts = req.getParameter("getmoreposts");
			String async =req.getParameter("isasync");
			
			if(async!=null)
				sync=!Boolean.parseBoolean(async);
			if(moreposts!=null)
				morepots=!Boolean.parseBoolean(moreposts);
			//req.par;
			if(req.getParameter("logout")!=null)
				logout= Boolean.parseBoolean( req.getParameter("logout"));
			
			if(numberofposts!=null&&morepots) {
				 numpost =Integer.parseInt(numberofposts)-11;
				 
			}
			else if(numberofposts!=null) {
				numpost =Integer.parseInt(numberofposts);
			}
				
			
			System.out.println("wants logout   "+logout);
			
			if(logout) {
				user=usecontrol.createguestuser();
				
				
				
			}
			
			
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
					
					sync=false;
					
					System.out.println("chatpassed      _ "+chatinput);
					System.out.println("chatpasseduser  _ "+user.getusername());
					//ArrayList<post> chatposts= (ArrayList<post>) chat.Getchat(0);
					
					//System.out.println("chatpassed      _ "+chatposts.get(chatposts.size()-1).Getpost());
					
					
					
				}
			}
			
			

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
		
		System.out.println("jsonobj      _"+jsonchstpost);
		System.out.println("username      _ "+user.getusername());
		//req.logout();
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		req.setAttribute("user", user);
	
		resp.setContentType("text/plain");
		resp.getWriter().println("");
		resp.getWriter().println(jsonchstpost);
		//resp.getWriter().close();
		//resp.getWriter().println(x);
		if (sync||logout) {
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
		//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		
		//errorMessage = null;
		//result = null;
	
	
	}//End of doPost//
	
	
	
	

}

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



public class pmservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("pm Servlet: doGet");	
		
		usser user = null;
		Integer userid = (Integer) req.getSession().getAttribute("userid");
		if(userid!=null) {
			UserController control=new UserController();
			user=control.getuserbyid(userid);
			
		}
	
		if(user==null) {//if user id was not found creates a new guest 
			user= new usser();
		}
		req.getSession().setAttribute("userid", user.getuserid());
		//
		
		
		// call JSP to generate empty form
		req.setAttribute("user", user);
		req.getRequestDispatcher("/_view/pmpage.jsp").forward(req, resp);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("pm Servlet: doPost");
		
		
		
		System.out.println("__________________________________________________________");
	
		
		usser user = null;
		Integer userid = (Integer) req.getSession().getAttribute("userid");
		if(userid!=null) {
			UserController control=new UserController();
			user=control.getuserbyid(userid);
			
		}
	
		if(user==null) {//if user id was not found creates a new guest 
			user= new usser();
		}
		//req.getSession().setAttribute("userid", user.getuserid());
		//
		
		
		try {
			
			
			
			
			String username =  req.getParameter("username");
			String password =  req.getParameter("password");
			
			
			
			
			
			System.out.println("username      _ "+username);
			System.out.println("assword      _ "+password);
		
			
			if(username!=null&&password!=null) {
				if(username.trim().length()>0&&password.trim().length()>0) {
					UserController loger=new UserController();
						usser temp=loger.loguserin(username, password);
						
					if(temp!=null) {
						user=temp;
					}
				}
			}
			
		}
		finally{
				
		}
		

		req.getSession().setAttribute("userid", user.getuserid());
		
		System.out.println("username      _ "+user.getusername());
		req.setAttribute("user", user);
		req.getRequestDispatcher("/_view/pmpage.jsp").forward(req, resp);
		
		
		
	}//End of doPost//
}


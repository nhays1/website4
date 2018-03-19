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

import website4.controller.chatcontroler;
import website4.controller.loginverification;
import website4.model.post;
import website4.model.usser;



public class Userinfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("userinfo Servlet: doGet");	
		
		usser user=new usser();
		
		req.setAttribute("user", user);
		
		
		
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/userinfo.jsp").forward(req, resp);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("userinfo Servlet: doPost");
		
		usser user=new usser();
		
		System.out.println("__________________________________________________________");
	
		
		
		
		
		try {
			
			String ussing =  req.getParameter("usreing");
			
			if(ussing!=null) {
				if(ussing!="") {
					user.setusername(ussing);
				}

				
			}
			
			
			String username =  req.getParameter("username");
			String password =  req.getParameter("password");
			
			
			
			System.out.println("ussing      _ "+ussing);
			
			System.out.println("username      _ "+username);
			System.out.println("assword      _ "+password);
		
			
			if(username!=null&&password!=null) {
				if(username.trim().length()>0&&password.trim().length()>0) {
					loginverification loger=new loginverification();
						usser temp=loger.loguserin(username, password);
						
					if(temp!=null) {
						user=temp;
					}
				}
			}
			
		}
		finally{
				
		}
		

		
		
		System.out.println("username      _ "+user.getusername());
		req.setAttribute("user", user);
		req.getRequestDispatcher("/_view/userinfo.jsp").forward(req, resp);
		
		
		
	}//End of doPost//
}


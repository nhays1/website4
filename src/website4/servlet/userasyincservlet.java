package website4.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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



public class userasyincservlet extends HttpServlet {
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
		System.out.println("__________________________________________________________");
		System.out.println("____userasinc Servlet: doPost");
		UserController control=new UserController();
		boolean isguest=true,getimg=false;
		Gson gson = new GsonBuilder().create();
		
		usser user = null;
		String imgstr=null;
		
		
		try {
			
			
			
			Integer userid = (Integer) req.getSession().getAttribute("userid");
			if(userid!=null) {
				
				user=control.getuserbyid(userid);
				
			}
		
			if(user==null) {//if user id was not found creates a new guest 
				user= new usser();
			}
			System.out.println("____userasinc first   id  "+user.getuserid());
			isguest=control.isguest(user.getuserid());
			
					
			
			
			String getim = req.getParameter("getimg");
			if(getim!=null) {
				getimg=Boolean.parseBoolean(getim);
				if(getimg) {
					
					imgstr=control.getuserimg(user.getuserid());
					//control.getuserbyid(user.getuserid());
					
					//System.out.println("dddddddddddddddd");
					
					
			
				}
				
			}
				
			
			
			
		}
		finally{
			
		}
		

		req.getSession().setAttribute("userid", user.getuserid());
		if(getimg) {
			resp.setContentType("text/plain");
			//resp.getWriter().println("");
			resp.getWriter().println(imgstr);
		}
		else {
			resp.setContentType("text/plain");
			resp.getWriter().println("");
			String obj;
			obj=isguest+" ";
			if(isguest) {
				obj+=" ";
			}
			obj+=user.getusername();
			resp.getWriter().println(obj);
		
		}
		
		
		
		
		
	}//End of doPost//
}


package website4.servlet;

import java.io.IOException;
import java.io.InputStream;
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



public class Userinfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("userinfo Servlet: doGet");	
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
		req.getRequestDispatcher("/_view/userinfo.jsp").forward(req, resp);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("userinfo Servlet: doPost");
		
		
		
		System.out.println("__________________________________________________________");
		UserController control=new UserController();
		
		usser user = null;
		Integer userid = (Integer) req.getSession().getAttribute("userid");
		if(userid!=null) {
			user=control.getuserbyid(userid);
			
		}
	
		if(user==null) {//if user id was not found creates a new guest 
			user= new usser();
		}
		//req.getSession().setAttribute("userid", user.getuserid());
		//
		String error = null;
		boolean sync = false;
		//1.40957  1.390  278 113
		try {
			
			String username = null,password = null;
			
			try {
				 username =  req.getParameter("username");
				 password =  req.getParameter("password");
			}
			catch (Exception e) {
				error="file to large max size is 130Kb";
			}
			
			System.out.println(error);
			
			
			System.out.println("username      _ "+username);
			System.out.println("assword      _ "+password);
		
			
			if(username!=null&&password!=null) {
				if(username.trim().length()>0&&password.trim().length()>0) {
						usser temp=control.loguserin(username, password);
						sync=true;
					if(temp!=null) {
						user=temp;
					}
				}
			}
			
			
			
			
			InputStream content =  req.getInputStream();
			
			
			String img = req.getParameter("img");
			//System.out.println(img);
			//System.out.println(img.getBytes());
			//in= req.getInputStream();
			if(img!=null) {
				sync=false;
				byte[] bytes = img.getBytes();
				
				//im.setBytes(pos, bytes)
				try {
					Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
					
					// Set autocommit to false to allow execution of
					// multiple queries/statements as part of the same transaction.
					conn.setAutoCommit(false);
					
					//System.out.println(new String(bytes, "UTF-8"));
					Blob im = conn.createBlob(); 
					im.setBytes(1, bytes);
					control.addimg(user.getuserid(),im);
					//System.out.println(im.getBytes(1, (int) im.length()).toString());
					//System.out.println(new String(im.getBytes(1, (int) im.length()), "UTF-8"));
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				
				
				//System.out.println(im);
				//System.out.println(img.length());
				//control.addimg(user.getuserid(),im);
			}
			
		}
		finally{
				
		}
		

		req.getSession().setAttribute("userid", user.getuserid());
		
		System.out.println("username      _ "+user.getusername());
		if(sync) {
			req.setAttribute("user", user);
			req.getRequestDispatcher("/_view/userinfo.jsp").forward(req, resp);
		}
		else {
			resp.setContentType("text/plain");
			resp.getWriter().print(error);
		}
		
	}//End of doPost//
}


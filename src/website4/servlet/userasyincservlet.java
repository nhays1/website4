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
		
		usser user = null;
		String imgstr=null;
		Blob img = null;
		
		
		try {
			
			
			// Set autocommit to false to allow execution of
			// multiple queries/statements as part of the same transaction.
			try {
				Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
				conn.setAutoCommit(false);
				img=conn.createBlob();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			
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
					Blob im = null;
					imgstr=control.getuserimg(user.getuserid());
					//control.getuserbyid(user.getuserid());
					
					//System.out.println("dddddddddddddddd");
					//System.out.println(img.length());
					try {
						Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
						conn.setAutoCommit(false);
						byte[] bytes=img.getBytes(1,(int) img.length());
						System.out.println(bytes);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*
					try {
						
						Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
						conn.setAutoCommit(false);
						//img=conn.createBlob();
						//img=control.getuserimg(user.getuserid());
						im=img;
						resp.setContentType("text/plain");
						resp.getWriter().println("");
						conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
						conn.setAutoCommit(false);
						System.out.println(new String(im.getBytes(1, (int) im.length()), "UTF-8"));
						//resp.getWriter().println(new String(img.getBytes(1,(int) img.length()), "UTF-8"));
						System.out.println("dddddddddddddddd");
						
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						System.out.println(new String(im.getBytes(1, (int) im.length()), "UTF-8"));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
				}
				
			}
				
			
			
			
		}
		finally{
			
		}
		try {
			//control.getuserbyid(user.getuserid());
			Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
			//conn.setAutoCommit(false);
			Blob temp=conn.createBlob();
			//temp=img;
			System.out.println(temp.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(getimg) {
			resp.setContentType("text/plain");
			//resp.getWriter().println("");
			resp.getWriter().println(imgstr);
		}
		else {
			resp.setContentType("text/plain");
			resp.getWriter().println("");
			resp.getWriter().println(isguest);
		
		}
		
		
		
		
		
	}//End of doPost//
}


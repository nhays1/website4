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
		boolean isguest=true;
		
		usser user = null;
	
		
		
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
			
					
				
				
				
			
			
			
		}
		finally{
				
		}
		

		
	
			resp.setContentType("text/plain");
			resp.getWriter().println("");
			resp.getWriter().println(isguest);
		
		
		
		
		
		
		
	}//End of doPost//
}


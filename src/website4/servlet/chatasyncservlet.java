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


public class chatasyncservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String result;
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("__________________________________________________________");
		System.out.println("chatasyncservlet Servlet: doPost");
		Integer numpost = 0 ;
		String chatname = null;
		chatcontroler chat =new chatcontroler();
		UserController usecontrol=new UserController();
		Gson gson = new GsonBuilder().create();
		
	
		usser user = null;
		//
		try {
			
			Integer userid = (Integer) req.getSession().getAttribute("userid");
			if(userid!=null) {
			
				
				user=usecontrol.getuserbyid(userid);
				if(user!=null)
					System.out.println("sesion usser     "+user.getusername());
			}
	
			if(user==null) {//if user id was not found creates a new guest 
				user=usecontrol.createguestuser() ;

			}
			//req.getSession().setAttribute("userid", userid);
			//
		}
		finally{
			
		}
		
		try {
			int toblock;
			String blok = req.getParameter("toblock");
			if(blok!=null) {
				toblock=Integer.parseInt(blok);
				chat.addtoblacklist(user.getuserid(), toblock);
			}
			chatname=req.getParameter("chatname");
			
	
		}
		finally{
			
		}
		

		
		ArrayList<post> chatposts;
		if(chatname==null) {
			chatname="general";
		}
		chatposts= (ArrayList<post>) chat.Getchat(numpost,chatname,user.getuserid());

		
		String jsonchstpost = gson.toJson(chatposts);

			resp.setContentType("text/plain");
			resp.getWriter().println("");
			resp.getWriter().println(jsonchstpost);
			System.out.println("----------------------------------------  _");
		
	
	}//End of doPost//
	
	
	
	

}

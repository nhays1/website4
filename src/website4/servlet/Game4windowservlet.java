package website4.servlet;

import java.io.IOException;
//import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import website4.controller.UserController;
import website4.controller.chatcontroler;
import website4.controller.highscorecontroller;
import website4.model.post;
import website4.model.usser;



public class Game4windowservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String gamename="3djavascript";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
		System.out.println("__________________________________________________________");
		System.out.println("game4 Servlet: DOGET");
		System.out.println("__________________________________________________________");
		int chatlength;
		chatcontroler chat =new chatcontroler();
		UserController usecontrol=new UserController();
		highscorecontroller scorectrl= new highscorecontroller();
		
		
		
		
		Gson gson = new GsonBuilder().create();
		String jsongamescore = gson.toJson(scorectrl.getgamehighscores(gamename));
		
		System.out.println(jsongamescore);
		
		
		
		ArrayList<post> chatposts;
		chatposts= (ArrayList<post>) chat.Getchat(0,"general");
		
		//
		usser user = null;
		Integer userid = (Integer) req.getSession().getAttribute("userid");
		if(userid!=null) {
		
			user=usecontrol.getuserbyid(userid);
			
		}
	
		if(user==null) {//if user id was not found creates a new guest 
			user=usecontrol.createguestuser();
			
		}
		String jsonuserscore=gson.toJson(scorectrl.getuserscores(gamename, user.getuserid()));
		req.getSession().setAttribute("userid", user.getuserid());
		//
		chatlength=chatposts.size();
		String jsonchstpost = gson.toJson(chatposts);
		
		//System.out.println("jsonobj      _"+jsonchstpost);
		
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("gemescores", jsongamescore);
		req.setAttribute("userscores", jsonuserscore);
		req.setAttribute("chatlength", chatlength);
		
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/Game4window.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("__________________________________________________________");
		System.out.println("Gamewindow Servlet: doPost");
		int chatlength;
		Integer numpost = 0 ;
		//boolean logout=false;
		
		int score = 0;
		boolean sync=true;
		
		chatcontroler chat =new chatcontroler();
		UserController usecontrol=new UserController();
		highscorecontroller scorectrl= new highscorecontroller();
		Gson gson = new GsonBuilder().create();
		String jsongamescore = "";
		String jsonuserscore="";
		
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
			String async =req.getParameter("isasync");
			
			if(async!=null)
				sync=!Boolean.parseBoolean(async);
			
			
			if(skore!=null) {
				score =(int) (Math.round( Double.valueOf(skore)));
				//userscore= scorectrl.getuserscores(gamename, user.getuserid());
				jsongamescore = gson.toJson(scorectrl.addscoretodb(gamename, user.getuserid(), score, user.getusername()));
				jsonuserscore=gson.toJson(scorectrl.addtouserscores(gamename, user.getuserid(), score));
				System.out.println("score json      _"+jsonuserscore);
			}
			System.out.println("score      _"+score);
			
			
		}
		finally{
			
		}
		
		

		
		ArrayList<post> chatposts;
		System.out.println("numpostsssss      _ "+numpost);
		chatposts= (ArrayList<post>) chat.Getchat(numpost,"general");
		System.out.println("numpostdddddd      _ "+chatposts.size());
		
		
		
		chatlength=chatposts.size();
		
		String jsonchstpost = gson.toJson(chatposts);
		
		
		req.getSession().setAttribute("userid", user.getuserid());
		
		//System.out.println("jsonobj      _"+jsonchstpost);
		System.out.println("username      _ "+user.getusername());
		//req.logout();
		
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		//userscore
		req.setAttribute("userscores", jsonuserscore);
		//req.setAttribute("userscores", jsonuserscore);
		req.setAttribute("user", user);
		req.setAttribute("gemescores", jsongamescore);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("");
		resp.getWriter().println(jsongamescore);
		//resp.getWriter().print(" ");
		resp.getWriter().println( jsonuserscore);
	
		
		// Forward to view to render the result HTML document
		if (sync) {
			req.getRequestDispatcher("/_view/Game4window.jsp").forward(req, resp);
		}
		
	}

	
	
}

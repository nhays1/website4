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

import website4.controller.chatcontroler;
import website4.model.post;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("index Servlet: doGet");
		int chatlength;
		chatcontroler chat =new chatcontroler();
		ArrayList<post> chatposts;
		chatposts= chat.Getchat();
		
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
		
		System.out.println("index Servlet: doPost");
		int chatlength;
		chatcontroler chat =new chatcontroler();
		
		try {
			
			
			String chatinput =  req.getParameter("chatinputtext");
			String username =  req.getParameter("username");
			String password =  req.getParameter("password");
			
			
			
			
			
			System.out.println("username      _ "+username);
			System.out.println("assword      _ "+password);
			System.out.println("chat      _ "+chatinput);
			
			if(username!=null&&password!=null) {
				if(username.trim().length()>0&&password.trim().length()>0) {
				
					
					
				}
			}
			if(chatinput!=null) {
				if(chatinput.trim().length()>0) {
					long now=Instant.now().toEpochMilli();
					//if()
					chat.makenewpost(now, "jmino", chatinput);
					System.out.println("chatpassed      _ "+chatinput);
					
					
					
					
					
				}
			}
			
			

		}
		finally{
			
		}
		
		

		
		ArrayList<post> chatposts;
		chatposts= chat.Getchat();
		
		chatlength=chatposts.size();
		
		Gson gson = new GsonBuilder().create();
		String jsonchstpost = gson.toJson(chatposts);
		
		//System.out.println("jsonobj      _"+jsonchstpost);
		
		req.setAttribute("chatposts", jsonchstpost);
		req.setAttribute("chatlength", chatlength);
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}

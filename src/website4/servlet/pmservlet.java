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
		System.out.println("__________________________________________________________");
		System.out.println("pm Servlet: doPost");
		UserController control=new UserController();
		boolean sync=true,morepots=false,getpmlist=false;
		int pmid=-1,numpost=0,pmchatid=-1;
		chatcontroler chat=new chatcontroler();
		Gson gson = new GsonBuilder().create();
		ArrayList<post> pmposts = null;
		String jsonpmlist=null;
		
		
		usser user = null;
		//req.getSessusser user = null;ion().setAttribute("userid", user.getuserid());
		//
		
		
		try {
			System.out.println("     pm is async   "+req.getParameter("isasync"));
			
			
			Integer userid = (Integer) req.getSession().getAttribute("userid");
			if(userid!=null) {
				
				user=control.getuserbyid(userid);
				
			}
		
			if(user==null) {//if user id was not found creates a new guest 
				user= new usser();
			}
			System.out.println("   pm first   id  "+user.getuserid());
		
			
			
			String pmi =  req.getParameter("pmid");
			if (pmi!=null) {
				pmid=Integer.parseInt(pmi);
			}
			System.out.println("   pm second   id  "+pmid);
			String pmir =  req.getParameter("pmcahtid");
			System.out.println("   pm chad   id  "+req.getParameter("pmcahtid"));
			if (pmir!=null&&pmir!="") {
				pmchatid=Integer.parseInt(pmir);
			}
			
			String pminput =  req.getParameter("pminput");
			pminput= control.escapestring(pminput);
			
			String numberofposts = req.getParameter("numberofpost");
			
			String moreposts = req.getParameter("getmoreposts");
			String async =req.getParameter("isasync");
			
			String pmlist=req.getParameter("getpmlist");
			
			
			if(pmlist!=null)
				getpmlist=Boolean.parseBoolean(pmlist);
			
			if(async!=null)
				sync=!Boolean.parseBoolean(async);
			
			if(moreposts!=null)
				morepots=Boolean.parseBoolean(moreposts);
			
			if(numberofposts!=null) {
				try {
					numpost =Integer.parseInt(numberofposts);
				}
				catch(Exception e){
					
				}
				
				if(morepots) {
					 numpost -=11;
				}
			}
			
			System.out.println("    pmid second user       _ "+pmid);
			
			if(pmchatid==-1) {
				//pmposts= (ArrayList<post>)chat.gotopm(user.getuserid(), pmid);
				pmchatid= chat.getpmid(user.getuserid(), pmid);
			}
			System.out.println("   pm chad   id fin     "+pmchatid);
			
			if(pminput!=null) {
				if(pminput.trim().length()>0) {
					long now=Instant.now().toEpochMilli();
					chat.posttopm(now, user.getuserid(), pminput, pmchatid);
					sync=false;
				
					System.out.println("   pmchatpassed      _ "+pminput);
					
				
				
				}
			}
			pmposts= (ArrayList<post>)chat.gotopm(pmchatid,user.getuserid());
			if(getpmlist) {
				jsonpmlist=gson.toJson(chat.getpmlist(user.getuserid()));
			}
			
		}
		finally{
				
		}
		

		req.getSession().setAttribute("userid", user.getuserid());
		req.setAttribute("pmchaid", pmchatid);
		
		System.out.println("    pmusername      _ "+user.getusername());
		req.setAttribute("user", user);
		String jsonpmpost= gson.toJson(pmposts);
		
		if(getpmlist) {
			resp.setContentType("text/plain");
			resp.getWriter().println("");
			resp.getWriter().println(jsonpmlist);
		}
		else {
			resp.setContentType("text/plain");
			resp.getWriter().println("");
			resp.getWriter().println(jsonpmpost);
		}
		
		
		
		if(sync)
			req.getRequestDispatcher("/_view/pmpage.jsp").forward(req, resp);
		
		
		
	}//End of doPost//
}


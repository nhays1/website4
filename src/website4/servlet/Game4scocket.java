package website4.servlet;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.annotation.WebServlet;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCode;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import website4.controller.UserController;
import website4.controller.pongwebscocketctrl;





// scocket example from   https://stackoverflow.com/questions/46009847/how-to-properly-report-an-error-to-client-through-websockets
@ServerEndpoint("/Game4scocket")
public class Game4scocket {
	private static final int startupmessages=2;
	private static final int identificatinobits=5;
    private javax.websocket.Session session;
    private int messagecont,myid,otherid,gameid,myplayernum;
    private pongwebscocketctrl multictrl= new pongwebscocketctrl();
    
    
    
    
    
    public void handshake(String message){
    	System.out.println(message+"   "+message.substring(0, identificatinobits)+"    "+message.substring(identificatinobits+1));
    	if(message.substring(0, identificatinobits).equals("othr ")) {
    		otherid=Integer.parseInt(message.substring(identificatinobits+1));
    		System.out.println(otherid);
    	}
    	if(message.substring(0, identificatinobits).equals("myid ")) {
    		myid=Integer.parseInt(message.substring(identificatinobits+1));
    		System.out.println(myid);
    	}
    	if(messagecont==startupmessages) {
    		gameid=multictrl.gotomultiplayer(myid, otherid);
    		myplayernum=multictrl.getplayernumber(myid, gameid);
    		System.out.println("   "+myid+" is player "+myplayernum+" in game number "+gameid);
    		 try {
 				session.getBasicRemote().sendText("plnum:"+myplayernum);
 	    	 } 
 	    	 catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 	    	 }
    	}
    }
    
    
    
    @OnOpen
    public void onWebSocketConnect(javax.websocket.Session session) throws Exception {
    	System.out.println("WebSocket opened: " + session.getId());
        this.session = session;
        messagecont=0;
      
     
    }
    
    @OnMessage
    public void onWebSocketText(String message){
    	 if (messagecont<startupmessages) {
    		 messagecont++;
    		 System.out.println("Received TEXT message: " + message+ " from scocket "+session.getId());
    		 handshake(message);
    	 }
    	 else if(message.substring(0, identificatinobits).equals("ball ")) {
    		 multictrl.setballstate(gameid, message.substring(identificatinobits+1));
    		 messagecont++;
    		 if( messagecont<5)
    			 System.out.println("Received TEXT message: " + message+ " from scocket "+session.getId());
     	}
    	 else if(message.substring(0, identificatinobits).equals("mypad")) {
    		 multictrl.setmypaddle(gameid, myplayernum, message.substring(identificatinobits+1));
    		 messagecont++;
    		 if( messagecont<5)
    			 System.out.println("Received TEXT message: " + message+ " from scocket "+session.getId());
      	}
    	
    	
    	
    	if(myplayernum==2) {
    		try {
 				session.getBasicRemote().sendText("ball :"+multictrl.getballstate(gameid));
 				session.getBasicRemote().sendText("otpad:"+multictrl.getotherpaddle(gameid, myplayernum));
 	    	 } 
 	    	 catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 	    	 }
    	}
    	else if(myplayernum==1) {
    		try {
 				session.getBasicRemote().sendText("otpad:"+multictrl.getotherpaddle(gameid, myplayernum));
 	    	 } 
 	    	 catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 	    	 }
    	}
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
    	System.out.println("Socket "+ session.getId()+" Closed: " + reason);
       // logger.info("WebSocket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable t){
    	System.out.println("WebSocket "+ session.getId()+" Error: ");
        t.printStackTrace();
    	
       // logger.debug(t, t);
        if (!session.isOpen()){
           // logger.info("Throwable in closed websocket:" + t, t);
            return;
        }

        CloseCode reason = t instanceof Exception ? CloseReason.CloseCodes.PROTOCOL_ERROR : CloseReason.CloseCodes.UNEXPECTED_CONDITION;
        try{
            session.close(new CloseReason(reason, t.getMessage()));
        }
        catch (IOException e){
        	System.out.println("IOException Error: ");
        	e.printStackTrace();
           // logger.warn(e, e);
        }

    }

}




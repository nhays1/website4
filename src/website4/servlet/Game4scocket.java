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






@ServerEndpoint("/Game4scocket")
public class Game4scocket {
    private javax.websocket.Session session;
    //private UserController usecontrol=new UserController();
    
    public void update(){
    	try {
			session.getBasicRemote().sendText("update");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    @OnOpen
    public void onWebSocketConnect(javax.websocket.Session session) throws Exception {
    	System.out.println("WebSocket opened: " + session.getId());
    	 //System.out.println("WebSocket opened: " + session);
        //logger.info("WebSocket connection attempt: " + session);
        this.session = session;
        update();
     
    }
    
    @OnMessage
    public void onWebSocketText(String message){
    	 System.out.println("Received TEXT message: " + message);
    	 
    	 try {
			session.getBasicRemote().sendText("pong");
		} 
    	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
    	System.out.println("Socket Closed: " + reason);
       // logger.info("WebSocket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable t){
    	System.out.println("WebSocket Error: ");
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




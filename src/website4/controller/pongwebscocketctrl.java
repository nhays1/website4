package website4.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.database.Triplet;
///import website4.database.DerbyDatabase.Transaction;
import website4.databsecontroler.InitDatabase;
import website4.model.usser;

public class pongwebscocketctrl {
	
	public pongwebscocketctrl() {
	
	}
	
	
	public static void main(String[] args) throws IOException {
		//String name="towerdef1";
		/*
		Gson gson = new GsonBuilder().create();
		String jsonchstpost = gson.toJson(updatemultiplayerlist(3) );
		System.out.println(jsonchstpost);
		
		gotomultiplayer(2);
		jsonchstpost = gson.toJson(updatemultiplayerlist(3) );
		System.out.println(jsonchstpost);
		
		
		leavemultiplayer(2);
		jsonchstpost = gson.toJson(updatemultiplayerlist(3) );
		System.out.println(jsonchstpost);
		*/
	}
	
	
	
	
	public  List<Triplet<String, Integer,Boolean>>  gotomultiplayer(int userid) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.gotomultiplayer(userid);
		return db.getmultyplayerlist(userid);
	}
	
	public void chalangeuser(int thisid,int idtochalange) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.chalangeuser(thisid, idtochalange);
	}
	
	public  void leavemultiplayer(int userid) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.leavemultyplayer(userid);
	}
	
	public  List<Triplet<String, Integer,Boolean>> updatemultiplayerlist(int userid){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		 List<Triplet<String, Integer,Boolean>> tmp=db.getmultyplayerlist(userid);
		 db.updatemultyplayertimes();
		return tmp;
	}
	public void acceptchalange(int acceptedby, int acceptedfrom) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.acceptchalange(acceptedfrom, acceptedby);
		//db.leavemultyplayer(acceptedfrom);
		
	}
	public int gotomultiplayer(int player1,int player2) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		Integer tmp=db.getmultiplayerid(player1, player2);
		if (tmp==null||tmp==-1) {
			db.createmultiplayer(player1, player2);
			tmp=db.getmultiplayerid(player1, player2);
			return tmp;
		}
		return tmp;
		
	}
	
	public int getplayernumber(int userid, int gameid) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getplayernumber(userid, gameid);
	}
	
	
	public void setballstate(int gameid,String ball) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.setballstate(ball, gameid);
	}
	public String getballstate(int gameid) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getballstate(gameid);
	}
	public void setmypaddle(int gameid, int playernumber, String paddlestste) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		if (playernumber==1) {
			db.setp1state(paddlestste, gameid);
		}
		else if (playernumber==2) {
			db.setp2state(paddlestste, gameid);
		}
	}
	public String getotherpaddle(int gameid, int myplayernum) {
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		if (myplayernum==1) {
			return db.getp2state(gameid);
		}
		else if (myplayernum==2) {
			return db.getp1state(gameid);
		}
		else return null;
	}
	
}



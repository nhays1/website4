package website4.controller;



import java.io.IOException;
import java.util.List;
import java.util.Map;


//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.databsecontroler.InitDatabase;



public class highscorecontroller {

	public highscorecontroller() {
		
	
	}
	public  List<Map.Entry<String, Integer>> getgamehighscores(String gamename){
		
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getper_game_scores(gamename);
		
	}
	
	public List<Integer> getuserscores(String gamename, int userid){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		return db.getperuserscores(gamename, userid);

	}
	

	public static void main(String[] args) throws IOException {
		//String name="towerdef1";
		//Gson gson = new GsonBuilder().create();
		//String jsonchstpost = gson.toJson(addtouserscores(name,4,50));
		//System.out.println(jsonchstpost);
		
	}
	
	public List<Map.Entry<String, Integer>> addscoretodb(String nameofthegame,int userid,int score,String  username){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		
		return db.addscoretogmaedb(nameofthegame, userid, score, username);
	}
	
	public List<Integer> addtouserscores(String nameofthegame,int userid,int score){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		db.addtouserscores(nameofthegame, userid, score);
		return db.getperuserscores(nameofthegame, userid);
	}
	
	
	
	
	
	
	
	
}

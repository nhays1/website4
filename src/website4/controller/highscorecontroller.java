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
		//List<Map.Entry<String, Integer>> scores=db.getper_game_scores(gamename);
		
		
		
		//new LinkedList(map.entrySet()) and 
		//Collections.sort(scores);
		//return scores;
		return db.getper_game_scores(gamename);
		
		
	}
	
	public static void main(String[] args) throws IOException {
		//String name="towerdef1";
		//Gson gson = new GsonBuilder().create();
		//String jsonchstpost = gson.toJson(getgamehighscores(name));
		//System.out.println(jsonchstpost);
		
		
	}
	
	public List<Map.Entry<String, Integer>> addscoretodb(String nameofthegame,int userid,int score,String  username){
		InitDatabase.init(1);
		IDatabase db = DatabaseProvider.getInstance();
		
		return db.addscoretogmaedb(nameofthegame, userid, score, username);
	}
	
	
	
	
	
	
	
	
	
	
}

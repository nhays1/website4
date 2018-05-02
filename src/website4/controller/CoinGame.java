package website4.controller;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import website4.database.DatabaseProvider;
import website4.database.IDatabase;
import website4.databsecontroler.InitDatabase;
import website4.model.usser;

public class CoinGame{
	
	private Random random;
	private int flip;
	private boolean isWin = false;
	private int reward = 0;
	private final double betMultiplier = 2;  //the bet multiplier is specific to this game, no other game (because some may be harder or easier) should have this multiplier, otherwise users are essentially playing the same game
	private int betAmnt = 0;
	private int headsOrTails = 0; //users selection
	
	/*
	 * Get form data from submitted HTML/JS for 
	 * - Bet Amount
	 * - Heads or Tails selection
	 */
	
	public void flip() {
		random = new Random();
		flip = random.nextInt(2);
	}
	
	public boolean getIsWin() {
		if(headsOrTails == flip) {	//if headsOrTails (user submitted selection) == flip, they won
			return true;
		}	
		return false;				//if not, they lost, and should lose what they bet
	}
	
	public int getReward() {		//gets reward based on result
			return (int)(betAmnt * betMultiplier);
	}
	
	public int getFlip() {
		return flip;
	}
	
	//get this from form submission
	public void setBet(int bet) {
		betAmnt = bet;
	}
	
	//get this from form submission
	public void setSelection(int selection) {
		headsOrTails = selection;
	}
	
}

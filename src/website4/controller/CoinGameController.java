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

public class CoinGameController{
	
	private Random random;
	private int flip;
	private boolean isWin = false;
	private int reward = 0;
	private double betMultiplier = 2;
	private int betAmnt = 0;
	private int headsOrTails; //users selection
	
	/*
	 * Get form data from submitted HTML/JS for 
	 * - Bet Amount
	 * - Heads or Tails selection
	 */
	
	public boolean getIsWin() {
		random = new Random();		//creates new random obj
		flip = random.nextInt(2);	//gives either a 1 or 2
		if(headsOrTails == flip) {	//if headsOrTails (user submitted selection) == flip, they won
			return true;
		}	
		return false;				//if not, they lost, and should lose what they bet
	}
	
	
	public int getReward() {		//gets reward based on result, and if 
		if(getIsWin() == true) {
			return (int)(betAmnt * betMultiplier);
		}
		return 0;
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

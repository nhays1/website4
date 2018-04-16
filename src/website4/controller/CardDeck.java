package website4.controller;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
	
	private ArrayList<Integer> deck = new ArrayList<Integer>();
	private Random rand = new Random();
	
	public void createDeck(){
		for(int i = 0; i < 10; i++){
			deck.add(i + 1);
		}
	}
	
	public Integer getCard(int i){
		return deck.get(i);
	}
	
	public void shuffleDeck(){
		
	}
	
}

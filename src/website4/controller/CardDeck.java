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
	
	public int size(){
		return deck.size();
	}
	
	public Integer getCard(int i){
		return deck.get(i);
	}
	
	public Integer getTopCard(){
		return deck.get(deck.size() - 1);
	}
	
	public void shuffleDeck(){
		for(int i = 0; i < deck.size(); i++){
			int index = rand.nextInt(deck.size());
			Integer temp = deck.get(i);
			deck.set(index, i);
			deck.set(i, temp);
		}
	}
	
}

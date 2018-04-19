package Cards;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private Card card;
	private Random rand = new Random();
	
	public void createDeck(){
		//HEARTS
		deck.add(card = new Card(Rank.ACE, Suit.HEARTS));
		deck.add(card = new Card(Rank.TWO, Suit.HEARTS));
		deck.add(card = new Card(Rank.THREE, Suit.HEARTS));
		deck.add(card = new Card(Rank.FOUR, Suit.HEARTS));
		deck.add(card = new Card(Rank.FIVE, Suit.HEARTS));
		deck.add(card = new Card(Rank.SIX, Suit.HEARTS));
		deck.add(card = new Card(Rank.SEVEN, Suit.HEARTS));
		deck.add(card = new Card(Rank.EIGHT, Suit.HEARTS));
		deck.add(card = new Card(Rank.NINE, Suit.HEARTS));
		deck.add(card = new Card(Rank.TEN, Suit.HEARTS));
		deck.add(card = new Card(Rank.JACK, Suit.HEARTS));
		deck.add(card = new Card(Rank.QUEEN, Suit.HEARTS));
		deck.add(card = new Card(Rank.KING, Suit.HEARTS));
		//DIAMONDS
		deck.add(card = new Card(Rank.ACE, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.TWO, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.THREE, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.FOUR, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.FIVE, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.SIX, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.SEVEN, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.EIGHT, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.NINE, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.TEN, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.JACK, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.QUEEN, Suit.DIAMONDS));
		deck.add(card = new Card(Rank.KING, Suit.DIAMONDS));
		//SPADES
		deck.add(card = new Card(Rank.ACE, Suit.SPADES));
		deck.add(card = new Card(Rank.TWO, Suit.SPADES));
		deck.add(card = new Card(Rank.THREE, Suit.SPADES));
		deck.add(card = new Card(Rank.FOUR, Suit.SPADES));
		deck.add(card = new Card(Rank.FIVE, Suit.SPADES));
		deck.add(card = new Card(Rank.SIX, Suit.SPADES));
		deck.add(card = new Card(Rank.SEVEN, Suit.SPADES));
		deck.add(card = new Card(Rank.EIGHT, Suit.SPADES));
		deck.add(card = new Card(Rank.NINE, Suit.SPADES));
		deck.add(card = new Card(Rank.TEN, Suit.SPADES));
		deck.add(card = new Card(Rank.JACK, Suit.SPADES));
		deck.add(card = new Card(Rank.QUEEN, Suit.SPADES));
		deck.add(card = new Card(Rank.KING, Suit.SPADES));
		//CLUBS
		deck.add(card = new Card(Rank.ACE, Suit.CLUBS));
		deck.add(card = new Card(Rank.TWO, Suit.CLUBS));
		deck.add(card = new Card(Rank.THREE, Suit.CLUBS));
		deck.add(card = new Card(Rank.FOUR, Suit.CLUBS));
		deck.add(card = new Card(Rank.FIVE, Suit.CLUBS));
		deck.add(card = new Card(Rank.SIX, Suit.CLUBS));
		deck.add(card = new Card(Rank.SEVEN, Suit.CLUBS));
		deck.add(card = new Card(Rank.EIGHT, Suit.CLUBS));
		deck.add(card = new Card(Rank.NINE, Suit.CLUBS));
		deck.add(card = new Card(Rank.TEN, Suit.CLUBS));
		deck.add(card = new Card(Rank.JACK, Suit.CLUBS));
		deck.add(card = new Card(Rank.QUEEN, Suit.CLUBS));
		deck.add(card = new Card(Rank.KING, Suit.CLUBS));
		
	}
	
	public CardDeck splitDeck(CardDeck d) {
		CardDeck splitDeck = d;
		for(int i = 0; i < d.size()/2; i++) {
			
		}
		return splitDeck;
	}
	
	public int size(){
		return deck.size();
	}
	
	public Card getCard(int i){
		return deck.get(i);
	}
	
	public Card getTopCard(){
		return deck.get(deck.size() - 1);
	}
	
	/*
	public void shuffleDeck(){
		for(int i = 0; i < deck.size(); i++){
			int index = rand.nextInt(deck.size());
			Card temp = deck.get(i);
			deck.set(index, i);
			deck.set(i, temp);
		}
	}
	*/
}

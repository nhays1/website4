package Cards;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private Card card;
	private Random rand = new Random();
	private ArrayList<Card> discards = new ArrayList<Card>();
	private int userBet = 0;
	private String toString = null;
	
	public CardDeck() {
		
	}
	
	public CardDeck(ArrayList<Card> cd) {
		this.deck = cd;
	}
	
	public int isWin(Card a, Card b) {
		if(a.compareTo(b) > 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public ArrayList<Card> createDeck(){
		//HEARTS
		deck.add(card = new Card(Rank.ACE, Suit.HEARTS, 1));
		deck.add(card = new Card(Rank.TWO, Suit.HEARTS, 2));
		deck.add(card = new Card(Rank.THREE, Suit.HEARTS, 3));
		deck.add(card = new Card(Rank.FOUR, Suit.HEARTS, 4));
		deck.add(card = new Card(Rank.FIVE, Suit.HEARTS, 5));
		deck.add(card = new Card(Rank.SIX, Suit.HEARTS, 6));
		deck.add(card = new Card(Rank.SEVEN, Suit.HEARTS, 7));
		deck.add(card = new Card(Rank.EIGHT, Suit.HEARTS, 8));
		deck.add(card = new Card(Rank.NINE, Suit.HEARTS, 9));
		deck.add(card = new Card(Rank.TEN, Suit.HEARTS, 10));
		deck.add(card = new Card(Rank.JACK, Suit.HEARTS, 11));
		deck.add(card = new Card(Rank.QUEEN, Suit.HEARTS, 12));
		deck.add(card = new Card(Rank.KING, Suit.HEARTS, 13));
		//DIAMONDS
		deck.add(card = new Card(Rank.ACE, Suit.DIAMONDS, 14));
		deck.add(card = new Card(Rank.TWO, Suit.DIAMONDS, 15));
		deck.add(card = new Card(Rank.THREE, Suit.DIAMONDS, 16));
		deck.add(card = new Card(Rank.FOUR, Suit.DIAMONDS, 17));
		deck.add(card = new Card(Rank.FIVE, Suit.DIAMONDS, 18));
		deck.add(card = new Card(Rank.SIX, Suit.DIAMONDS, 19));
		deck.add(card = new Card(Rank.SEVEN, Suit.DIAMONDS, 20));
		deck.add(card = new Card(Rank.EIGHT, Suit.DIAMONDS, 21));
		deck.add(card = new Card(Rank.NINE, Suit.DIAMONDS, 22));
		deck.add(card = new Card(Rank.TEN, Suit.DIAMONDS, 23));
		deck.add(card = new Card(Rank.JACK, Suit.DIAMONDS, 24));
		deck.add(card = new Card(Rank.QUEEN, Suit.DIAMONDS, 25));
		deck.add(card = new Card(Rank.KING, Suit.DIAMONDS, 26));
		//SPADES
		deck.add(card = new Card(Rank.ACE, Suit.SPADES, 27));
		deck.add(card = new Card(Rank.TWO, Suit.SPADES, 28));
		deck.add(card = new Card(Rank.THREE, Suit.SPADES, 29));
		deck.add(card = new Card(Rank.FOUR, Suit.SPADES, 30));
		deck.add(card = new Card(Rank.FIVE, Suit.SPADES, 31));
		deck.add(card = new Card(Rank.SIX, Suit.SPADES, 32));
		deck.add(card = new Card(Rank.SEVEN, Suit.SPADES, 33));
		deck.add(card = new Card(Rank.EIGHT, Suit.SPADES, 34));
		deck.add(card = new Card(Rank.NINE, Suit.SPADES, 35));
		deck.add(card = new Card(Rank.TEN, Suit.SPADES, 36));
		deck.add(card = new Card(Rank.JACK, Suit.SPADES, 37));
		deck.add(card = new Card(Rank.QUEEN, Suit.SPADES, 38));
		deck.add(card = new Card(Rank.KING, Suit.SPADES, 39));
		//CLUBS
		deck.add(card = new Card(Rank.ACE, Suit.CLUBS, 40));
		deck.add(card = new Card(Rank.TWO, Suit.CLUBS, 41));
		deck.add(card = new Card(Rank.THREE, Suit.CLUBS, 42));
		deck.add(card = new Card(Rank.FOUR, Suit.CLUBS, 43));
		deck.add(card = new Card(Rank.FIVE, Suit.CLUBS, 44));
		deck.add(card = new Card(Rank.SIX, Suit.CLUBS, 45));
		deck.add(card = new Card(Rank.SEVEN, Suit.CLUBS, 46));
		deck.add(card = new Card(Rank.EIGHT, Suit.CLUBS, 47));
		deck.add(card = new Card(Rank.NINE, Suit.CLUBS, 48));
		deck.add(card = new Card(Rank.TEN, Suit.CLUBS, 49));
		deck.add(card = new Card(Rank.JACK, Suit.CLUBS, 50));
		deck.add(card = new Card(Rank.QUEEN, Suit.CLUBS, 51));
		deck.add(card = new Card(Rank.KING, Suit.CLUBS, 52));
		
		return deck;
	}
	
	
	public ArrayList<Card> splitDeck(ArrayList<Card> cd) {
		ArrayList<Card> splitDeck = new ArrayList<Card>();
		for(int i = 0; i < cd.size()/2; i++) {
			splitDeck.add(i, cd.get(i));
			cd.remove(cd.get(i));
		}
		this.deck = cd;
		return splitDeck;
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	public void addCard(Card c) {
		
	}
	
	public int size(){
		return deck.size();
	}
	
	public Card pullCard() { //pulls top card from a deck and removes that card from deck
		Card returnCard = deck.get(deck.size() - 1);
		deck.remove(deck.get(deck.size() - 1));
		return returnCard;
	}
	
	public Card getCard(int i){
		return deck.get(i);
	}
	
	public Card getTopCard(){
		return deck.get(deck.size() - 1);
	}
	
	
	public void shuffleDeck(){
		for(int i = 0; i < deck.size(); i++){
			int index = rand.nextInt(deck.size());
			Card temp = deck.get(i);
			deck.set(index, deck.get(i));
			deck.set(i, temp);
		}
	}

	public void setBet(int userBet) {
		this.userBet = userBet;
	}
	
	public void flip(ArrayList<Card> cardArrayList) {
		
	}
}

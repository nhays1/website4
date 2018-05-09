package Cards;


public class Card implements Comparable<Card> {
	private Rank rank;
	private Suit suit;
	private int cardIndex;
	/**
	 * Constructor.
	 * 
	 * @param rank the card's rank
	 * @param suit the card's suit
	 */
	public Card(Rank rank, Suit suit, int cardIndex) {
		this.rank = rank;
		this.suit = suit;
		this.cardIndex = cardIndex;
	}

	/**
	 * @return the card's {@link Rank}
	 */
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * @return the card's {@link Suit}
	 */
	public Suit getSuit() {
		return suit;
	}
	
	public int getCardIndex() {
		return cardIndex;
	}
	
	@Override
	public String toString() {
		return rank.toString() + suit.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Card)) {
			return false;
		}
		Card other = (Card) obj;
		return this.compareTo(other) == 0;
	}
	
	public int getRankToCompare() {
		
		if(this.getRank() == Rank.ACE) {
			return 1;
		}
		else if(this.getRank() == Rank.TWO) {
			return 2;
		}
		else if(this.getRank() == Rank.THREE) {
			return 3;
		}
		else if(this.getRank() == Rank.FOUR) {
			return 4;
		}
		else if(this.getRank() == Rank.FIVE) {
			return 5;
		}
		else if(this.getRank() == Rank.SIX) {
			return 6;
		}
		else if(this.getRank() == Rank.SEVEN) {
			return 7;
		}
		else if(this.getRank() == Rank.EIGHT) {
			return 8;
		}
		else if(this.getRank() == Rank.NINE) {
			return 9;
		}
		else if(this.getRank() == Rank.TEN) {
			return 10;
		}
		else if(this.getRank() == Rank.JACK) {
			return 11;
		}
		else if(this.getRank() == Rank.QUEEN) {
			return 12;
		}
		else if(this.getRank() == Rank.KING) {
			return 13;
		}
		else {
			return 0;
		}
		
	}

	public int compareTo(Card o) {
		return 0;
	}

	
}

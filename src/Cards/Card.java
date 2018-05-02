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
	
	public int compareTo(Card o) {
		int cmp1 = this.rank.compareTo(o.rank);
		int cmp2 = this.suit.compareTo(o.suit);
		if(cmp1 == 0) {
			return cmp1;
		}
		else if (cmp2 == 0) {
			return cmp2;
		}
		return this.rank.compareTo(o.rank);
	}
}

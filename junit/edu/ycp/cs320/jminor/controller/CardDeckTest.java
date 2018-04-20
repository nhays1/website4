package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Cards.Card;
import Cards.CardDeck;
import Cards.Rank;
import Cards.Suit;

public class CardDeckTest {
	
	private CardDeck cd1;
	private CardDeck cd2;
	Card testNum;
	Card testNum2;
	
	@Before
	public void setUp(){
		cd1 = new CardDeck();
		cd2 = new CardDeck();
		testNum = new Card(Rank.ACE, Suit.HEARTS, 1);
		testNum2 = new Card(Rank.TWO, Suit.HEARTS, 2);
	}
	
	@Test
	public void testCreate(){
		cd1.createDeck();
		assertEquals(testNum, cd1.getCard(0));
		assertEquals(testNum2, cd1.getCard(1));
	}
	/*
	@Test
	public void testShuffle(){
		cd1.createDeck();
		Card test1 = cd1.getCard(0);
		Card test2 = cd1.getCard(1);
		Card test3 = cd1.getCard(2);
		Card test4 = cd1.getCard(3);
		Card test5 = cd1.getCard(4);
		Card test6 = cd1.getCard(5);
		Card test7 = cd1.getCard(6);
		
		cd1.shuffleDeck();
		assertNotEquals(test1, cd1.getCard(0));
		assertNotEquals(test2, cd1.getCard(1));
		assertNotEquals(test3, cd1.getCard(2));
		assertNotEquals(test4, cd1.getCard(3));
		assertNotEquals(test5, cd1.getCard(4));
		assertNotEquals(test6, cd1.getCard(5));
		assertNotEquals(test7, cd1.getCard(6));
	}
	*/
	@Test
	public void testGetTopCard(){
		cd1.createDeck();
		Card top = new Card(Rank.KING, Suit.CLUBS, 52);
		assertEquals(top, cd1.getTopCard());
	}
	
	@Test
	public void testSplitDeck() {
		cd1.createDeck();
		cd2 = new CardDeck(cd1.splitDeck(cd1.getDeck()));
	}
	
}

package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Cards.Card;
import Cards.CardDeck;

public class CardDeckTest {
	
	private CardDeck cd1;
	private CardDeck cd2;
	Integer testNum;
	Integer testNum2;
	
	@Before
	public void setUp(){
		cd1 = new CardDeck();
		cd2 = new CardDeck();
		testNum = 1;
		testNum2 = 7;
	}
	
	@Test
	public void testCreate(){
		cd1.createDeck();
		assertEquals(testNum, cd1.getCard(0));
		assertEquals(testNum2, cd1.getCard(6));
	}
	
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
		
		/*
		cd1.shuffleDeck();
		assertNotEquals(test1, cd1.getCard(0));
		assertNotEquals(test1, cd1.getCard(1));
		assertNotEquals(test1, cd1.getCard(2));
		assertNotEquals(test1, cd1.getCard(3));
		assertNotEquals(test1, cd1.getCard(4));
		assertNotEquals(test1, cd1.getCard(5));
		assertNotEquals(test1, cd1.getCard(6));
		*/
	}
	
	@Test
	public void testGetTopCard(){
		cd1.createDeck();
		Integer ten = 10;
		assertEquals(ten, cd1.getTopCard());
	}
	
}

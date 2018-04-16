package edu.ycp.cs320.jminor.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import website4.controller.CardDeck;

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
	
}

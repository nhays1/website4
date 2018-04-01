package edu.ycp.cs320.jminor.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import website4.controller.CoinGame;


public class CoinGameControllerTest {
	
	private CoinGame cg;
	int bet1;
	int bet2;
	boolean isWin;

	@Before
	public void setUp() {
		bet1 = 100;
		bet2 = 10;
		cg = new CoinGame();
	}
	
	/*
	 * 
	 * JUST A HEADS UP, IF BOTH TESTS PASS, THAT MEANS BOTH TESTS GUESSED AND FLIPPED CORRECTLY,
	 * SAME THING IF THEY FAILED. this took me like 7 minutes to figure out, i thought i messed up but its supposed
	 * to happen.
	 * 
	 */
	
	@Test
	public void testHeadsGuess() {
		cg.setBet(bet1);
		cg.setSelection(0); //1 is Heads
		cg.flip();			//flips coin
		cg.getIsWin();		//compares the two
		assertEquals(true, cg.getIsWin());  //sometimes fails, only when the coin flips to tails
	}
	
	@Test
	public void testTailsGuess() {
		cg.setBet(bet2);
		cg.setSelection(1); //0 is Tails
		cg.flip();			//flips coin, could be 0 or 1
		cg.getIsWin();		//compares selection and flip
		assertEquals(true, cg.getIsWin());  //sometimes fails, only when the coin flips to heads
	}
	
}

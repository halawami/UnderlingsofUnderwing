package tests.game;

import static org.junit.Assert.*;

import org.junit.Test;

import underlings.game.Handler;
import underlings.game.HandlerState;

public class HandlerTests {

	@Test
	public void testInitialState() {
		Handler handler = new Handler();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
	@Test
	public void testMoveToUnclaimedAndUnhatchedEggFromReadyRoom(){
		Handler handler = new Handler();
		handler.moveToCard();
		assertEquals(HandlerState.CARD, handler.getState());
	}
	
	@Test
	public void testMoveToFieldFromReadyRoom(){
		Handler handler = new Handler();
		handler.moveToField();
		assertEquals(HandlerState.FIELD, handler.getState());
	}

}

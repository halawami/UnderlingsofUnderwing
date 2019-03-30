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

	@Test
	public void testMoveToFieldWhiteFromReadyRoom(){
		Handler handler = new Handler();
		handler.moveToFieldWhite();
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
	}
	
	@Test
	public void testMoveToReadyRoomFromReadyRoom(){
		Handler handler = new Handler();
		handler.moveToReadyRoom();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
	@Test
	public void testMoveToIllegalStateFromReadyRoom(){
		Handler handler = new Handler();
		handler.moveToBreakRoom();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
}

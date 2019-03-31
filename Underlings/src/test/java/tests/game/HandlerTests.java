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
	public void testMoveToUnclaimedAndUnhatchedEggFromReadyRoom() {
		Handler handler = new Handler();
		handler.moveToCard();
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testMoveToFieldFromReadyRoom() {
		Handler handler = new Handler();
		handler.moveToField();
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testMoveToFieldWhiteFromReadyRoom() {
		Handler handler = new Handler();
		handler.moveToFieldWhite();
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
	}

	@Test
	public void testMoveToReadyRoomFromReadyRoom() {
		Handler handler = new Handler();
		handler.moveToReadyRoom();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromReadyRoom() {
		Handler handler = new Handler();
		handler.moveToBreakRoom();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
		handler.moveToIncubation();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testMoveClockwiseInField() {
		Handler handler = new Handler();
		handler.moveToField();
		handler.moveClockwiseInField();
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testMoveToBreakRoomFromField() {
		Handler handler = new Handler();
		handler.moveToField();
		handler.moveToBreakRoom();
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromField() {
		Handler handler = new Handler();
		handler.moveToField();
		handler.moveToReadyRoom();
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testMoveToBreakRoomFromCard() {
		Handler handler = new Handler();
		handler.moveToCard();
		handler.moveToBreakRoom();
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegateStateFromCard() {
		Handler handler = new Handler();
		handler.moveToCard();
		handler.moveToReadyRoom();
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testMoveToCardFromCard() {
		Handler handler = new Handler();
		handler.moveToCard();
		handler.moveToCard();
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testMoveToReadyRoomFromBreakRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.moveToReadyRoom();
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromBreakRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.moveToCard();
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToBreakRoomFromFieldWhiteSpace() {
		Handler handler = new Handler();
		handler.moveToFieldWhite();
		handler.moveToBreakRoom();
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromFieldWhiteSpace() {
		Handler handler = new Handler();
		handler.moveToFieldWhite();
		handler.moveToReadyRoom();
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
	}

	@Test
	public void testMoveToIncubationFromIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToIncubation();
		assertEquals(HandlerState.INCUBATION, handler.getState());
	}
	
	@Test
	public void testMoveToIllegalStateFromIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToField();
		assertEquals(HandlerState.INCUBATION, handler.getState());
	}
}

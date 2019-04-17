package tests.handler.movement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.game.Handler;
import underlings.game.HandlerState;

public class StateTests {

	@Test
	public void testReadyRoomToUnclaimedEgg() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testReadyRoomToReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testReadyRoomToFieldWhite() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD_WHITESPACE);
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
	}

	@Test
	public void testReadyRoomToField() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testFieldMoveClockwise() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		handler.moveClockwiseInField();
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testFieldToBreakRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}


	@Test
	public void testCardToBreakRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testCardToCard() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		handler.moveToState(HandlerState.CARD);
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testBreakRoomToReadyRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testFieldWhiteSpaceToBreakRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD_WHITESPACE);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testIncubationToIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToState(HandlerState.INCUBATION);
		assertEquals(HandlerState.INCUBATION, handler.getState());
	}

	@Test
	public void testIncubationToReadyRoom() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}


	
}

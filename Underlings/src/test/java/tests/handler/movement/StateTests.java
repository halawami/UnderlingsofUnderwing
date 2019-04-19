package tests.handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import underlings.game.Handler;
import underlings.game.HandlerState;

public class StateTests {

	@Test
	public void testReadyRoomPossibleStates() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		List<HandlerState> possibleStates = handler.getPossibleStates();
		assertEquals(4, possibleStates.size());
		assertTrue(possibleStates.contains(HandlerState.CARD));
		assertTrue(possibleStates.contains(HandlerState.READY_ROOM));
		assertTrue(possibleStates.contains(HandlerState.FIELD));
		assertTrue(possibleStates.contains(HandlerState.FIELD_WHITESPACE));
	}

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
		this.testMove(HandlerState.READY_ROOM, HandlerState.FIELD);
	}

	@Test
	public void testFieldPossibleStates() {
		Handler handler = new Handler(HandlerState.FIELD);
		List<HandlerState> possibleStates = handler.getPossibleStates();
		assertEquals(2, possibleStates.size());
		assertTrue(possibleStates.contains(HandlerState.FIELD));
		assertTrue(possibleStates.contains(HandlerState.BREAK_ROOM));
	}

	@Test
	public void testFieldMoveClockwise() {
		this.testMove(HandlerState.FIELD, HandlerState.FIELD);
	}

	@Test
	public void testFieldToBreakRoom() {
		this.testMove(HandlerState.FIELD, HandlerState.BREAK_ROOM);
	}

	@Test
	public void testCardPossibleStates() {
		Handler handler = new Handler(HandlerState.CARD);
		List<HandlerState> possibleStates = handler.getPossibleStates();
		assertEquals(2, possibleStates.size());
		assertTrue(possibleStates.contains(HandlerState.CARD));
		assertTrue(possibleStates.contains(HandlerState.BREAK_ROOM));
	}

	@Test
	public void testCardToBreakRoom() {
		this.testMove(HandlerState.CARD, HandlerState.BREAK_ROOM);
	}

	@Test
	public void testCardToCard() {
		this.testMove(HandlerState.CARD, HandlerState.CARD);
	}

	@Test
	public void testBreakRoomPossibleStates() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		List<HandlerState> possibleStates = handler.getPossibleStates();
		assertEquals(1, possibleStates.size());
		assertTrue(possibleStates.contains(HandlerState.READY_ROOM));
	}

	@Test
	public void testBreakRoomToReadyRoom() {
		this.testMove(HandlerState.BREAK_ROOM, HandlerState.BREAK_ROOM);
	}

	@Test
	public void testFieldWhiteSpacePossibleStates() {
		Handler handler = new Handler(HandlerState.FIELD_WHITESPACE);
		List<HandlerState> possibleStates = handler.getPossibleStates();
		assertEquals(1, possibleStates.size());
		assertTrue(possibleStates.contains(HandlerState.BREAK_ROOM));
	}

	@Test
	public void testFieldWhiteSpaceToBreakRoom() {
		this.testMove(HandlerState.FIELD_WHITESPACE, HandlerState.BREAK_ROOM);
	}

	@Test
	public void testIncubationPossibleStates() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		List<HandlerState> possibleStates = handler.getPossibleStates();
		assertEquals(2, possibleStates.size());
		assertTrue(possibleStates.contains(HandlerState.INCUBATION));
		assertTrue(possibleStates.contains(HandlerState.READY_ROOM));
	}

	@Test
	public void testIncubationToIncubation() {
		this.testMove(HandlerState.INCUBATION, HandlerState.INCUBATION);
	}

	@Test
	public void testIncubationToReadyRoom() {
		this.testMove(HandlerState.INCUBATION, HandlerState.READY_ROOM);
	}

	public void testMove(HandlerState from, HandlerState to) {
		Handler handler = new Handler(from);
		handler.moveToState(to);
		assertEquals(to, handler.getState());
	}

}

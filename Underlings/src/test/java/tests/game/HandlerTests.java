package tests.game;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import underlings.game.Handler;
import underlings.game.HandlerState;

public class HandlerTests {

	@Test 
	public void testInitializeHashMap(){
		Handler handler = new Handler(HandlerState.READY_ROOM);
		HashMap<HandlerState, List<HandlerState>> map = handler.initializeHashMap();
		assertEquals(6, map.size());
	}
	
	@Test
	public void testCreateStateList(){
		Handler handler = new Handler(HandlerState.READY_ROOM);
		List<HandlerState> list = handler.createStateList(HandlerState.READY_ROOM);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testMoveToUnclaimedAndUnhatchedEggFromReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testMoveToFieldFromReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testMoveToFieldWhiteFromReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD_WHITESPACE);
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
	}

	@Test
	public void testMoveToReadyRoomFromReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
		handler.moveToState(HandlerState.INCUBATION);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testMoveClockwiseInField() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		handler.moveClockwiseInField();
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testMoveToBreakRoomFromField() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromField() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.FIELD, handler.getState());
	}

	@Test
	public void testMoveToBreakRoomFromCard() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegateStateFromCard() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testMoveToCardFromCard() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.CARD);
		handler.moveToState(HandlerState.CARD);
		assertEquals(HandlerState.CARD, handler.getState());
	}

	@Test
	public void testMoveToReadyRoomFromBreakRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromBreakRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.moveToState(HandlerState.CARD);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToBreakRoomFromFieldWhiteSpace() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD_WHITESPACE);
		handler.moveToState(HandlerState.BREAK_ROOM);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}

	@Test
	public void testMoveToIllegalStateFromFieldWhiteSpace() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.FIELD_WHITESPACE);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
	}

	@Test
	public void testMoveToIncubationFromIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToState(HandlerState.INCUBATION);
		assertEquals(HandlerState.INCUBATION, handler.getState());
	}
	
	@Test
	public void testMoveToReadyRoomFromIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToState(HandlerState.READY_ROOM);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
	@Test
	public void testMoveToIllegalStateFromIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		handler.moveToState(HandlerState.FIELD);
		assertEquals(HandlerState.INCUBATION, handler.getState());
	}
}
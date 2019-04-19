package tests.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.game.FieldSpace;
import underlings.game.Handler;
import underlings.game.HandlerState;
import underlings.gui.DrawChoice;

public class StringTests {
	@Test
	public void testToStringReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		assertEquals("Handler in Ready Room", handler.toString());
	}

	@Test
	public void testToStringBreakRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		assertEquals("Handler in Break Room", handler.toString());
	}

	@Test
	public void testToStringCard() {
		Handler handler = new Handler(HandlerState.CARD);
		handler.setLocation("testCard");
		assertEquals("Handler on testCard", handler.toString());
	}

	@Test
	public void testToStringIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		assertEquals("Handler in Incubation", handler.toString());
	}

	@Test
	public void testToStringFieldBlue() {
		Handler handler = new Handler(HandlerState.FIELD);
		FieldSpace fieldSpace = new FieldSpace(DrawChoice.BLUE);
		fieldSpace.addHandler(handler);
		assertEquals("Handler on BLUE Field Space", handler.toString());
	}

	@Test
	public void testToStringFieldRed() {
		Handler handler = new Handler(HandlerState.FIELD);
		FieldSpace fieldSpace = new FieldSpace(DrawChoice.RED);
		fieldSpace.addHandler(handler);
		assertEquals("Handler on RED Field Space", handler.toString());
	}
}
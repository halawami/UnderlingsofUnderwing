package tests.elements.giver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import underlings.element.ElementGiver;
import underlings.game.FieldSpace;
import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class HandlerTests {

	@Test
	public void testNotField() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		ElementGiver elementGiver = handler.elementGiver;
		List<DrawChoice> drawChoices = elementGiver.drawChoices;
		assertEquals(1, drawChoices.size());
		assertTrue(drawChoices.contains(DrawChoice.RANDOM));
	}

	@Test
	public void testFieldBlue() {
		testFieldColor(DrawChoice.BLUE);
	}

	@Test
	public void testFieldRed() {
		testFieldColor(DrawChoice.RED);
	}

	@Test
	public void testFieldGreen() {
		testFieldColor(DrawChoice.GREEN);
	}

	@Test
	public void testFieldPurple() {
		testFieldColor(DrawChoice.PURPLE);
	}

	@Test
	public void testFieldOrange() {
		testFieldColor(DrawChoice.ORANGE);
	}

	@Test
	public void testFieldYellow() {
		testFieldColor(DrawChoice.YELLOW);
	}

	@Test
	public void testFieldBlack() {
		testFieldColor(DrawChoice.BLACK);
	}

	@Test
	public void testFieldWhite() {
		testFieldColor(DrawChoice.WHITE);
	}

	private void testFieldColor(DrawChoice color) {
		FieldSpace fieldSpace = new FieldSpace(color);
		Handler handler = new Handler(HandlerState.FIELD);
		fieldSpace.addHandler(handler);
		ElementGiver elementGiver = handler.elementGiver;
		List<DrawChoice> drawChoices = elementGiver.drawChoices;
		assertEquals(2, drawChoices.size());
		assertTrue(drawChoices.contains(DrawChoice.RANDOM));
		assertTrue(drawChoices.contains(color));
	}
	
	@Test
	public void testToStringReadyRoom() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		ElementGiver elementGiver = handler.elementGiver;
		assertEquals("Handler in Ready Room", elementGiver.toString());
	}

	@Test
	public void testToStringBreakRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		ElementGiver elementGiver = handler.elementGiver;
		assertEquals("Handler in Break Room", elementGiver.toString());
	}

	@Test
	public void testToStringCard() {
		Handler handler = new Handler(HandlerState.CARD);
		handler.setLocation("testCard");
		ElementGiver elementGiver = handler.elementGiver;
		assertEquals("Handler on testCard", elementGiver.toString());
	}

	@Test
	public void testToStringIncubation() {
		Handler handler = new Handler(HandlerState.INCUBATION);
		ElementGiver elementGiver = handler.elementGiver;
		assertEquals("Handler in Incubation", elementGiver.toString());
	}

	@Test
	public void testToStringFieldBlue() {
		Handler handler = new Handler(HandlerState.FIELD);
		FieldSpace fieldSpace = new FieldSpace(DrawChoice.BLUE);
		fieldSpace.addHandler(handler);
		ElementGiver elementGiver = handler.elementGiver;
		assertEquals("Handler on BLUE Field Space", elementGiver.toString());
	}

	@Test
	public void testToStringFieldRed() {
		Handler handler = new Handler(HandlerState.FIELD);
		FieldSpace fieldSpace = new FieldSpace(DrawChoice.RED);
		fieldSpace.addHandler(handler);
		ElementGiver elementGiver = handler.elementGiver;
		assertEquals("Handler on RED Field Space", elementGiver.toString());
	}

}

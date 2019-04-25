package tests.field;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import underlings.field.Field;
import underlings.field.FieldSpaceFactory;
import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class RotateTests {

	@Test
	public void testRotateStart() {
		Field field = new Field(new FieldSpaceFactory());
		Handler handler = new Handler(HandlerState.READY_ROOM);
		
		field.addHandler(0, handler);
		field.rotate(handler);
		
		assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.BLUE));
	}
	
	@Test
	public void testRotateEnd() {
		Field field = new Field(new FieldSpaceFactory());
		Handler handler = new Handler(HandlerState.READY_ROOM);
		
		field.addHandler(21, handler);
		field.rotate(handler);
		
		assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.RED));
	}
	
}

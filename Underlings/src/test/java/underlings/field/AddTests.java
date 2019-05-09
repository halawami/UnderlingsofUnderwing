package underlings.field;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class AddTests {

	private Field field;
	private Handler handler;

	@Before
	public void init() {
		this.field = new Field(new FieldSpaceFactory());
		this.handler = new Handler(HandlerState.READY_ROOM);
	}

	@Test
	public void testStart() {
		this.field.addHandler(0, this.handler);
		assertTrue(this.handler.elementGiver.drawChoices.contains(DrawChoice.RED));
	}

	@Test
	public void testEnd() {
		this.field.addHandler(21, this.handler);
		assertTrue(this.handler.elementGiver.drawChoices.contains(DrawChoice.GREEN));
	}

	@Test
	public void testWhite() {
		this.field.addHandlerWhitespace(this.handler);
		assertTrue(this.handler.elementGiver.drawChoices.contains(DrawChoice.WHITE));
	}

}

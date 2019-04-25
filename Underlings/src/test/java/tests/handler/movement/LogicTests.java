package tests.handler.movement;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;

public class LogicTests {

	@Test
	public void testReadyRoom() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		GUI gui = EasyMock.mock(GUI.class);
		HandlerMovementLogic logic = new HandlerMovementLogic(hatchingGround, gui);
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		
		EasyMock.replay(hatchingGround, gui);
		
		logic.move(handler, HandlerChoice.READY_ROOM);
		
		EasyMock.verify(hatchingGround, gui);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
}

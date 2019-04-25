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
	public void testBreakRoom() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		GUI gui = EasyMock.mock(GUI.class);
		HandlerMovementLogic logic = new HandlerMovementLogic(hatchingGround, gui);
		Handler handler = new Handler(HandlerState.CARD);
		
		EasyMock.replay(hatchingGround, gui);
		
		logic.move(handler, HandlerChoice.BREAK_ROOM);
		
		EasyMock.verify(hatchingGround, gui);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}
	
	@Test
	public void testStay() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		GUI gui = EasyMock.mock(GUI.class);
		HandlerMovementLogic logic = new HandlerMovementLogic(hatchingGround, gui);
		Handler handler = new Handler(HandlerState.READY_ROOM);
		
		EasyMock.replay(hatchingGround, gui);
		
		logic.move(handler, HandlerChoice.STAY);
		
		EasyMock.verify(hatchingGround, gui);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
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

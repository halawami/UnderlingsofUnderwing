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
	
}

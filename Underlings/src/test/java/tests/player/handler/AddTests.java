package tests.player.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.game.HandlerFactory;
import underlings.player.Player;

public class AddTests {

	@Test
	public void test2Handlers() {
		Player player = new Player(6, new HandlerFactory());
		player.addHandler();
		
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void testMaxMinus1Handlers() {
		Player player = new Player(6, new HandlerFactory());
		player.addHandler();
		player.addHandler();
		player.addHandler();
		
		player.addHandler();
		assertEquals(6, player.getHandlerCount());
	}
	
	@Test
	public void testMaxHandler() {
		Player player = new Player(6, new HandlerFactory());
		player.addHandler();
		player.addHandler();
		player.addHandler();
		player.addHandler();
		
		player.addHandler();
		assertEquals(6, player.getHandlerCount());
	}
	
}

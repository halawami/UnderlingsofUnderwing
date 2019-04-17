package tests.player.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.game.HandlerFactory;
import underlings.player.Player;

public class AddTests {

	private Player player;

	@Before
	public void init() {
		this.player = new Player(6, new HandlerFactory());
	}
	
	@Test
	public void test2Handlers() {
		this.gainHandler(1);
		assertEquals(3, this.player.getHandlerCount());
	}
	
	@Test
	public void testMaxMinus1Handlers() {
		this.gainHandler(3);
		
		this.player.gainHandler();
		assertEquals(6, this.player.getHandlerCount());
	}
	
	@Test
	public void testMaxHandler() {
		this.gainHandler(5);
		
		this.player.gainHandler();
		assertEquals(6, this.player.getHandlerCount());
	}
	
	private void gainHandler(int amt) {
		for (int i = 0; i < amt; i++) {
			this.player.gainHandler();
		}
	}
}

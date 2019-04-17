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
		this.addHandler(1);
		assertEquals(3, this.player.getHandlerCount());
	}
	
	@Test
	public void testMaxMinus1Handlers() {
		this.addHandler(3);
		
		this.player.addHandler();
		assertEquals(6, this.player.getHandlerCount());
	}
	
	@Test
	public void testMaxHandler() {
		this.addHandler(5);
		
		this.player.addHandler();
		assertEquals(6, this.player.getHandlerCount());
	}
	
	private void addHandler(int amt) {
		for (int i = 0; i < amt; i++) {
			this.player.addHandler();
		}
	}
}

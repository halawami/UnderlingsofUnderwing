package tests.player.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.game.HandlerFactory;
import underlings.player.Player;

public class RemoveTests {

	private Player player;

	@Before
	public void init() {
		this.player = new Player(6, new HandlerFactory());
	}

	@Test
	public void test3Handlers() {
		this.addHandler(1);
		
		this.player.loseHandler();
		assertEquals(2, this.player.getHandlerCount());
	}

	@Test
	public void testMaxHandlers() {
		this.addHandler(4);
		
		this.player.loseHandler();
		assertEquals(5, this.player.getHandlerCount());
	}
	
	private void addHandler(int amt) {
		for (int i = 0; i < amt; i++) {
			this.player.addHandler();
		}
	}

}

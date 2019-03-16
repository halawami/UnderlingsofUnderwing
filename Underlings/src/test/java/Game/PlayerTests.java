package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTests {

	@Test
	public void testInitialHandlerCount() {
		Player player = new Player();
		assertEquals(2, player.getHandlerCount());
	}
}

package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandlerTests {

	@Test
	public void testHandlerInit() {
		Player player = new Player();
		assertEquals(2, player.getHandlerCount());
	}

}

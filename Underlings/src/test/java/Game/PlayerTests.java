package Game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PlayerTests {

	@Test
	public void testInitialHandlerCount() {
		Player player = new Player(4);
		assertEquals(2, player.getHandlerCount());
	}

	@Test
	public void testAddHandlerAfterInit() {
		Player player = new Player(4);
		player.addHandler();
		assertEquals(3, player.getHandlerCount());
	}

	@Test
	public void testGetHandlerListSize() {
		Player player = new Player(4);
		player.addHandler();
		List<Handler> handlers = player.getHandlers();
		assertEquals(handlers.size(), player.getHandlerCount());
	}

	@Test
	public void testAddHandlerMaxSize() {
		Player player = new Player(6);
		player.addHandler();
		player.addHandler();
		player.addHandler();
		player.addHandler();
		assertEquals(6, player.getHandlerCount());
		player.addHandler();
		assertEquals(6, player.getHandlerCount());
	}
}

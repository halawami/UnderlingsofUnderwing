package Game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PlayerTests {

	@Test
	public void testInitialHandlerCount() {
		Player player = new Player();
		assertEquals(2, player.getHandlerCount());
	}
	
	@Test
	public void testAddHandlerAfterInit(){
		Player player = new Player();
		player.addHandler();
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void testGetHandlerListSize(){
		Player player = new Player();
		List<Handler> handlers = player.getHandlers();
		assertEquals(handlers.size(), player.getHandlerCount());
	}
}

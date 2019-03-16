package Game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameTests {
	
	@Test
	public void testSetup2Players15Rounds() {
		Game game = new Game();
		game.setUp(2);
		
		assertEquals(game.getRoundsLeft(), 15);	
	}
	
	@Test
	public void testSetup3Players13Rounds() {
		Game game = new Game();
		game.setUp(3);
		
		assertEquals(game.getRoundsLeft(), 13);
	}

}

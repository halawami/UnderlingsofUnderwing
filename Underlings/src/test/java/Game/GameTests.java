package Game;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

public class GameTests {
	
	private Game game;
	
	@Before
	public void init() {
		this.game = new Game();
	}
	
	@Test 
	public void testSetup2Players_Rounds() {
		this.game.setUp(2);
		assertEquals(this.game.getRoundsLeft(), 15);	
	}
	
	@Test
	public void testSetup3Players_Rounds() {
		this.game.setUp(3);
		assertEquals(this.game.getRoundsLeft(), 13);
	}
	
	@Test
	public void testSetup4Players_Rounds() {
		this.game.setUp(4);
		assertEquals(this.game.getRoundsLeft(), 12);
	}

	@Test 
	public void testSetup6Players_Rounds() {
		this.game.setUp(6);
		assertEquals(this.game.getRoundsLeft(), 12);
	}

}

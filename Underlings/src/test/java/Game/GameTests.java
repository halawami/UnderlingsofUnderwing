package Game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

	@Test
	public void testSetup1Player() {
		assertThrows(IllegalArgumentException.class, () -> this.game.setUp(1));
	}
	
	@Test
	public void testSetup7Players() {
		assertThrows(IllegalArgumentException.class, () -> this.game.setUp(7));
	}
	
	@Test
	public void testSetup2Players_HatchingGround() {
		this.game.setUp(2);
		assertEquals(this.game.getHatchingGround().getWidth(), 3);
		assertEquals(this.game.getHatchingGround().getHeight(), 2);
	}
	
	@Test
	public void testSetup3Players_HatchingGround() {
		this.game.setUp(3);
		assertEquals(this.game.getHatchingGround().getWidth(), 4);
		assertEquals(this.game.getHatchingGround().getHeight(), 3);
	}
	
	@Test
	public void testSetup4Players_HatchingGround() {
		this.game.setUp(4);
		assertEquals(this.game.getHatchingGround().getWidth(), 4);
		assertEquals(this.game.getHatchingGround().getHeight(), 4);
	}
	
	@Test
	public void testSetup6Players_HatchingGround() {
		this.game.setUp(6);
		assertEquals(this.game.getHatchingGround().getWidth(), 4);
		assertEquals(this.game.getHatchingGround().getHeight(), 4);
	}
	
	@Test
	public void testSetup2Players_Players() {
		this.game.setUp(2);
		assertEquals(this.game.getPlayers().size(), 2);
	}
	
	@Test
	public void testSetup2Players_Handlers() {
		this.game.setUp(2);
		
		for (Player player : game.getPlayers()) {
			assertEquals(player.getHandlerCount(), 2);
		}
	}
}

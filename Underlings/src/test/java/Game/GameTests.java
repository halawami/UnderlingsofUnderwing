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
		assertEquals(15, this.game.getRoundsLeft());	
	}
	
	@Test
	public void testSetup3Players_Rounds() {
		this.game.setUp(3);
		assertEquals(13, this.game.getRoundsLeft());
	}
	
	@Test
	public void testSetup4Players_Rounds() {
		this.game.setUp(4);
		assertEquals(12, this.game.getRoundsLeft());
	}

	@Test 
	public void testSetup6Players_Rounds() {
		this.game.setUp(6);
		assertEquals(12, this.game.getRoundsLeft());
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
		assertEquals(3, this.game.getHatchingGround().getWidth());
		assertEquals(2, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup3Players_HatchingGround() {
		this.game.setUp(3);
		assertEquals(4, this.game.getHatchingGround().getWidth());
		assertEquals(3, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup4Players_HatchingGround() {
		this.game.setUp(4);
		assertEquals(4, this.game.getHatchingGround().getWidth());
		assertEquals(4, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup6Players_HatchingGround() {
		this.game.setUp(6);
		assertEquals(4, this.game.getHatchingGround().getWidth());
		assertEquals(4, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup2Players_Players() {
		this.game.setUp(2);
		assertEquals(2, this.game.getPlayers().size());
	}
	
	@Test
	public void testSetup6Players_Players() {
		this.game.setUp(6);
		assertEquals(6, this.game.getPlayers().size());
	}
	
	@Test
	public void testSetup2Players_Handlers() {
		this.game.setUp(2);
		
		for (Player player : game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
	@Test
	public void testSetup6Players_Handlers() {
		this.game.setUp(6);
		
		for (Player player : game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
}

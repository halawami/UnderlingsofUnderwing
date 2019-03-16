package Game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class GameTests {
	
	private Game game;
	private CardFactory cardFactory;
	
	@Before
	public void init() {
		this.game = new Game();
		cardFactory = EasyMock.mock(CardFactory.class);
		EasyMock.expect(cardFactory.getCards()).andStubReturn(new Stack<Card>());
	}
	
	@Test 
	public void testSetup2Players_Rounds() {
		this.game.setUp(2, cardFactory);
		assertEquals(15, this.game.getRoundsLeft());	
	}
	
	@Test
	public void testSetup3Players_Rounds() {
		this.game.setUp(3, cardFactory);
		assertEquals(13, this.game.getRoundsLeft());
	}
	
	@Test
	public void testSetup4Players_Rounds() {
		this.game.setUp(4, cardFactory);
		assertEquals(12, this.game.getRoundsLeft());
	}

	@Test 
	public void testSetup6Players_Rounds() {
		this.game.setUp(6, cardFactory);
		assertEquals(12, this.game.getRoundsLeft());
	}

	@Test
	public void testSetup1Player() {
		assertThrows(IllegalArgumentException.class, () -> this.game.setUp(1, cardFactory));
	}
	
	@Test
	public void testSetup7Players() {
		assertThrows(IllegalArgumentException.class, () -> this.game.setUp(7, cardFactory));
	}
	
	@Test
	public void testSetup2Players_HatchingGround() {
		this.game.setUp(2, cardFactory);
		assertEquals(3, this.game.getHatchingGround().getWidth());
		assertEquals(2, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup3Players_HatchingGround() {
		this.game.setUp(3, cardFactory);
		assertEquals(4, this.game.getHatchingGround().getWidth());
		assertEquals(3, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup4Players_HatchingGround() {
		this.game.setUp(4, cardFactory);
		assertEquals(4, this.game.getHatchingGround().getWidth());
		assertEquals(4, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup6Players_HatchingGround() {
		this.game.setUp(6, cardFactory);
		assertEquals(4, this.game.getHatchingGround().getWidth());
		assertEquals(4, this.game.getHatchingGround().getHeight());
	}
	
	@Test
	public void testSetup2Players_Players() {
		this.game.setUp(2, cardFactory);
		assertEquals(2, this.game.getPlayers().size());
	}
	
	@Test
	public void testSetup6Players_Players() {
		this.game.setUp(6, cardFactory);
		assertEquals(6, this.game.getPlayers().size());
	}
	
	@Test
	public void testSetup2Players_Handlers() {
		this.game.setUp(2, cardFactory);
		
		for (Player player : game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
	@Test
	public void testSetup6Players_Handlers() {
		this.game.setUp(6, cardFactory);
		
		for (Player player : game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
	@Test
	public void testSetupCardFactory(){
		
	}
	
}

package Game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import GUI.GUI;

public class GameTests {
	
	private Game game;
	private HatchingGround hatchingGround;
	private Stack<Card> cards;
	private GUI gui;
	
	@Before
	public void init() {
		this.gui = EasyMock.mock(GUI.class);
		
		this.cards = new Stack<Card>();
		for(int i = 0; i < 50; i++){
			this.cards.push(new Card());
		}

		this.hatchingGround = new HatchingGround(new Deck(this.cards));
		this.game = new Game(this.gui, this.hatchingGround);
		
	}	
	
	@Test
	public void testStart2Players() {
		EasyMock.expect(this.gui.promptPlayerCount()).andReturn(2);
		
		EasyMock.replay(this.gui);
		this.game.start();
		
		// VERIFY
		EasyMock.verify(this.gui);
		
		assertEquals(2, this.game.getPlayerCount());
		
		for (Player player : this.game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
			assertEquals(4, player.getMaxHandlers());
		}
		
		assertEquals(3, this.game.getHatchingGround().getWidth());
		assertEquals(2, this.game.getHatchingGround().getHeight());
		
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
		
		for (Player player : this.game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
	
	@Test
	public void testSetupHandlerState(){
		this.game.setUp(2);
		for(Player player : this.game.getPlayers()){
			for(Handler handler : player.getHandlers()){
				assertEquals(HandlerState.READY_ROOM, handler.getState());
			}
		}
	}
	
	@Test
	public void testSetup6Players_Handlers() {
		this.game.setUp(6);
		
		for (Player player : this.game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
	@Test
	public void testSetup2Players_MaxHandlers() {
		this.game.setUp(2);
		for(Player player : this.game.getPlayers()){
			assertEquals(4, player.getMaxHandlers());
		}
	}
	
	@Test
	public void testSetup3Players_MaxHandlers() {
		this.game.setUp(3);
		for(Player player : this.game.getPlayers()){
			assertEquals(5, player.getMaxHandlers());
		}
	}
	
	@Test

	public void testSetup4Players_MaxHandlers() {
		this.game.setUp(4);
		for(Player player : this.game.getPlayers()){
			assertEquals(6, player.getMaxHandlers());
		}
	}
	
	@Test
	public void testSetup6Players_MaxHandlers() {
		this.game.setUp(6);
		for(Player player : this.game.getPlayers()){
			assertEquals(6, player.getMaxHandlers());
		}
	}


}

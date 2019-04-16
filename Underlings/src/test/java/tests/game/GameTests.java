package tests.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Random;
import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.game.Card;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HandlerFactory;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.player.PlayerFactory;

public class GameTests {
	
	private Game game;
	private HatchingGround hatchingGround;
	private Stack<Card> cards;
	private GUI gui;
	
	@Before
	public void init() {
		Display mockedDisplay = EasyMock.mock(Display.class);
		PromptHandler mockedPrompts = EasyMock.mock(PromptHandler.class);
		this.gui = new GUI(mockedPrompts, mockedDisplay);
		
		this.cards = new Stack<Card>();
		for(int i = 0; i < 50; i++){
			this.cards.push(new Card());
		}

		this.hatchingGround = new HatchingGround(new Deck(this.cards));
		this.game = new Game(this.gui, this.hatchingGround, new PlayerFactory(new HandlerFactory()), new ElementBag(new ElementFactory(), new Random()));
		
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
	

	
	

	
	
	




}

package tests.gui;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;

public class GUITests {

	private Game game;
	private HatchingGround hatchingGround;
	private GUI gui;
	private Deck deck;

	@Before
	public void init() {
		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
		Display display = EasyMock.mock(Display.class);
		
		this.gui = new GUI(promptHandler, display);

		this.deck = EasyMock.mock(Deck.class);
		EasyMock.expect(this.deck.draw()).andStubReturn(new Card());
		EasyMock.replay(this.deck);

		this.hatchingGround = new HatchingGround(this.deck);
		this.game = new Game(this.gui, this.hatchingGround, new PlayerFactory(new HandlerFactory()), new ElementBag(new ElementFactory(), new Random()));
	}

	@Test
	public void testGetPlayerCountTwoPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptInt("Enter Player Count", 2, 6)).andReturn(2);

		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.game.promptPlayerCount();

		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);
		assertEquals(2, this.game.getPlayerCount());

	}

	@Test
	public void testGetPlayerCountSixPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptInt("Enter Player Count", 2, 6)).andReturn(6);

		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.game.promptPlayerCount();

		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);
		assertEquals(6, this.game.getPlayerCount());

	}

	@Test
	public void testDisplayCardSetupTwoPlayers() {
		this.game.setUp(2);

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(2);
		this.gui.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(6);

		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);

	}

	@Test
	public void testDisplayCardSetupThreePlayers() {
		this.game.setUp(3);

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(2);
		this.gui.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(12);

		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);

	}

	@Test
	public void testDisplayCardSetupFourPlayers() {
		this.game.setUp(4);

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(4);
		this.gui.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(16);

		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);

	}

	@Test
	public void testDisplayCardSetupSixPlayers() {
		this.game.setUp(6);

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(6);
		this.gui.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(16);

		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);

	}

//	@Test
//	public void testDisplayPlayerSetupTwoPlayers() {
//		this.game.setUp(2);
//		
//		// RECORD
//		EasyMock.expect(this.gui.promptHandler.promptPlayerCount()).andStubReturn(2);
//		this.gui.display.displayPlayer(EasyMock.anyInt(), EasyMock.anyObject(Player.class));
//		EasyMock.expectLastCall().times(2);
//		this.gui.display.displayHandlers(EasyMock.anyInt(), EasyMock.<List<Handler>>anyObject());
//		EasyMock.expectLastCall().anyTimes();
//
//		// REPLAY
//		EasyMock.replay(this.gui.promptHandler, this.gui.display);
//		this.game.displayPlayers();
//
//		// VERIFY
//		EasyMock.verify(this.gui.promptHandler, this.gui.display);
//	}
//	
//	@Test
//	public void testDisplayPlayerSetupSixPlayers() {
//		this.game.setUp(6);
//		
//		// RECORD
//		EasyMock.expect(this.gui.promptHandler.promptPlayerCount()).andStubReturn(6);
//		this.gui.display.displayPlayer(EasyMock.anyInt(), EasyMock.anyObject(Player.class));
//		EasyMock.expectLastCall().times(6);
//		this.gui.display.displayHandlers(EasyMock.anyInt(), EasyMock.<List<Handler>>anyObject());
//		EasyMock.expectLastCall().anyTimes();
//
//		// REPLAY
//		EasyMock.replay(this.gui.promptHandler, this.gui.display);
//		this.game.displayPlayers();
//
//		// VERIFY
//		EasyMock.verify(this.gui.promptHandler, this.gui.display);
//	}
//	
//	@Test
//	public void testDisplayHandlersSetupTwoPlayers() {
//		this.game.setUp(2);
//		
//		// RECORD
//		EasyMock.expect(this.gui.promptHandler.promptPlayerCount()).andStubReturn(2);
//		this.gui.display.displayPlayer(EasyMock.anyInt(), EasyMock.anyObject(Player.class));
//		EasyMock.expectLastCall().anyTimes();
//		this.gui.display.displayHandlers(EasyMock.anyInt(), EasyMock.<List<Handler>>anyObject());
//		EasyMock.expectLastCall().times(2);
//
//		// REPLAY
//		EasyMock.replay(this.gui.promptHandler, this.gui.display);
//		this.game.displayPlayers();
//
//		// VERIFY
//		EasyMock.verify(this.gui.promptHandler, this.gui.display);
//	}
//	
//	@Test
//	public void testDisplayHandlersSetupSixPlayers() {
//		this.game.setUp(6);
//		
//		// RECORD
//		EasyMock.expect(this.gui.promptHandler.promptPlayerCount()).andStubReturn(2);
//		this.gui.display.displayPlayer(EasyMock.anyInt(), EasyMock.anyObject(Player.class));
//		EasyMock.expectLastCall().anyTimes();
//		this.gui.display.displayHandlers(EasyMock.anyInt(), EasyMock.<List<Handler>>anyObject());
//		EasyMock.expectLastCall().times(6);
//
//		// REPLAY
//		EasyMock.replay(this.gui.promptHandler, this.gui.display);
//		this.game.displayPlayers();
//
//		// VERIFY
//		EasyMock.verify(this.gui.promptHandler, this.gui.display);
//	}
}

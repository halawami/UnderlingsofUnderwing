package GUI;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import Game.Card;
import Game.Deck;
import Game.Game;
import Game.HatchingGround;
import Player.Player;
import Player.PlayerFactory;

public class GUITests {

	private Game game;
	private HatchingGround hatchingGround;
	private GUI gui;
	private Deck deck;

	@Before
	public void init() {
		this.gui = EasyMock.mock(GUI.class);

		this.deck = EasyMock.mock(Deck.class);
		EasyMock.expect(this.deck.draw()).andStubReturn(new Card());
		EasyMock.replay(this.deck);

		this.hatchingGround = new HatchingGround(this.deck);
		this.game = new Game(this.gui, this.hatchingGround, new PlayerFactory());
	}

	@Test
	public void testGetPlayerCountTwoPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andReturn(2);

		// REPLAY
		EasyMock.replay(this.gui);
		this.game.requestPlayerCount();

		// VERIFY
		EasyMock.verify(this.gui);
		assertEquals(2, this.game.getPlayerCount());

	}

	@Test
	public void testGetPlayerCountSixPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andReturn(6);

		// REPLAY
		EasyMock.replay(this.gui);
		this.game.requestPlayerCount();

		// VERIFY
		EasyMock.verify(this.gui);
		assertEquals(6, this.game.getPlayerCount());

	}

	@Test
	public void testDisplayCardTwoPlayers() {
		this.game.setUp(2);

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andStubReturn(2);
		this.gui.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(6);

		// REPLAY
		EasyMock.replay(this.gui);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui);

	}

	@Test
	public void testDisplayCardThreePlayers() {
		this.game.setUp(3);

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andStubReturn(2);
		this.gui.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(12);

		// REPLAY
		EasyMock.replay(this.gui);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui);

	}

	@Test
	public void testDisplayCardFourPlayers() {
		this.game.setUp(4);

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andStubReturn(2);
		this.gui.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(16);

		// REPLAY
		EasyMock.replay(this.gui);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui);

	}

	@Test
	public void testDisplayCardSixPlayers() {
		this.game.setUp(6);

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andStubReturn(2);
		this.gui.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(16);

		// REPLAY
		EasyMock.replay(this.gui);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.gui);

	}

	@Test
	public void testDisplayPlayerTwoPlayers() {
		this.game.setUp(2);
		
		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andStubReturn(2);
		this.gui.displayPlayer(EasyMock.anyInt(), EasyMock.anyObject(Player.class));
		EasyMock.expectLastCall().times(2);

		// REPLAY
		EasyMock.replay(this.gui);
		this.game.displayPlayers();

		// VERIFY
		EasyMock.verify(this.gui);
	}
	
	@Test
	public void testDisplayPlayerSixPlayers() {
		this.game.setUp(6);
		
		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andStubReturn(6);
		this.gui.displayPlayer(EasyMock.anyInt(), EasyMock.anyObject(Player.class));
		EasyMock.expectLastCall().times(6);

		// REPLAY
		EasyMock.replay(this.gui);
		this.game.displayPlayers();

		// VERIFY
		EasyMock.verify(this.gui);
	}
}

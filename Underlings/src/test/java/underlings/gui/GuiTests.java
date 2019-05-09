package underlings.gui;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;

public class GuiTests {

	private Game game;
	private HatchingGround hatchingGround;
	private Gui gui;
	private Deck deck;
	private PromptHandler promptHandler;
	private Display display;

	@Before
	public void init() {
		this.promptHandler = EasyMock.mock(PromptHandler.class);
		this.display = EasyMock.mock(Display.class);
		this.gui = new Gui(this.promptHandler, this.display);

		this.deck = EasyMock.mock(Deck.class);
		EasyMock.expect(this.deck.draw()).andStubReturn(new Card());
		EasyMock.replay(this.deck);

		this.hatchingGround = new HatchingGround(this.deck);
		this.game = new Game(this.gui, this.hatchingGround, new PlayerFactory(new HandlerFactory()),
				new ElementBag(new ElementFactory(), new Random()));
	}

	@After
	public void verify() {
		EasyMock.verify(this.promptHandler, this.display);
	}

	@Test
	public void testGetPlayerCountTwoPlayers() {

		// RECORD
		EasyMock.expect(this.promptHandler.promptInt("Enter Player Count", 2, 6)).andReturn(2);

		// REPLAY
		EasyMock.replay(this.promptHandler, this.display);
		this.game.promptPlayerCount();

		// VERIFY
		EasyMock.verify(this.promptHandler, this.display);
		assertEquals(2, this.game.getPlayerCount());

	}

	@Test
	public void testGetPlayerCountSixPlayers() {

		// RECORD
		EasyMock.expect(this.promptHandler.promptInt("Enter Player Count", 2, 6)).andReturn(6);

		// REPLAY
		EasyMock.replay(this.promptHandler, this.display);
		this.game.promptPlayerCount();

		// VERIFY
		EasyMock.verify(this.promptHandler, this.display);
		assertEquals(6, this.game.getPlayerCount());

	}

	@Test
	public void testDisplayCardSetupTwoPlayers() {
		this.game.setUp(2);

		// RECORD
		EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(2);
		this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(6);

		// REPLAY
		EasyMock.replay(this.promptHandler, this.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.promptHandler, this.display);

	}

	@Test
	public void testDisplayCardSetupThreePlayers() {
		this.game.setUp(3);

		// RECORD
		EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(2);
		this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(12);

		// REPLAY
		EasyMock.replay(this.promptHandler, this.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.promptHandler, this.display);

	}

	@Test
	public void testDisplayCardSetupFourPlayers() {
		this.game.setUp(4);

		// RECORD
		EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(4);
		this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(16);

		// REPLAY
		EasyMock.replay(this.promptHandler, this.display);
		this.hatchingGround.display(this.gui);

		// VERIFY
		EasyMock.verify(this.promptHandler, this.display);

	}

	@Test
	public void testDisplayCardSetupSixPlayers() {
		this.game.setUp(6);

		EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(6);
		this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall().times(16);

		EasyMock.replay(this.promptHandler, this.display);
		this.hatchingGround.display(this.gui);
	}

	@Test
	public void testNotify() {

		this.promptHandler.displayMessage("test", 0, JOptionPane.PLAIN_MESSAGE);
		EasyMock.replay(this.promptHandler, this.display);

		this.gui.notifyAction(0, "test");
	}
}

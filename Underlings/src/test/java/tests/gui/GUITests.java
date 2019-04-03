package tests.gui;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.game.Game;
import underlings.game.HandlerFactory;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.player.PlayerFactory;

public class GUITests {

	private Game game;
	private HatchingGround hatchingGround;
	private GUI gui;
	
	@Before
	public void init() {
		PromptHandler mockedPrompts = EasyMock.mock(PromptHandler.class);
		Display mockedDisplay = EasyMock.mock(Display.class);
		
		this.gui = new GUI(mockedPrompts, mockedDisplay);
		this.hatchingGround = EasyMock.mock(HatchingGround.class);
		this.game = new Game(this.gui, this.hatchingGround, new PlayerFactory(new HandlerFactory()));
	}
	
	@Test
	public void testGetPlayerCountTwoPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptPlayerCount()).andReturn(2);
		
		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.game.start();
		
		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);
		assertEquals(2, this.game.getPlayerCount());
		
	}
	
	@Test
	public void testGetPlayerCountSixPlayers() {
		
		// RECORD
		EasyMock.expect(this.gui.promptHandler.promptPlayerCount()).andReturn(6);
		
		// REPLAY
		EasyMock.replay(this.gui.promptHandler, this.gui.display);
		this.game.start();
		
		// VERIFY
		EasyMock.verify(this.gui.promptHandler, this.gui.display);
		assertEquals(6, this.game.getPlayerCount());
		
	}
}

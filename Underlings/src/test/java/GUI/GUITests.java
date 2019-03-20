package GUI;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import Game.Game;
import Game.HatchingGround;

public class GUITests {

	private Game game;
	private HatchingGround hatchingGround;
	private GUI gui;
	
	@Before
	public void init() {
		this.gui = EasyMock.mock(GUI.class);
		this.hatchingGround = EasyMock.mock(HatchingGround.class);
		this.game = new Game(this.gui, this.hatchingGround);
	}
	
	@Test
	public void testGetPlayerCountTwoPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andReturn(2);
		
		// REPLAY
		EasyMock.replay(this.gui);
		this.game.start();
		
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
		this.game.start();
		
		// VERIFY
		EasyMock.verify(this.gui);
		assertEquals(6, this.game.getPlayerCount());
		
	}
}

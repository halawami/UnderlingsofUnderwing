package tests.game.setup;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.game.Game;
import underlings.game.HandlerFactory;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class HandlerTests {

	private Game game;
	
	@Before
	public void init() {
		this.game = new Game(EasyMock.mock(GUI.class), EasyMock.mock(HatchingGround.class), new PlayerFactory(new HandlerFactory()), EasyMock.mock(ElementBag.class));
	}	
	
	@Test
	public void testSetup2Players_Handlers() {
		this.game.setUp(2);
		
		for (Player player : this.game.getPlayers()) {
			assertEquals(2, player.getHandlerCount());
		}
	}
	
}

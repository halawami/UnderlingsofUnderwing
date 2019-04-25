package tests.phase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.handler.HandlerFactory;
import underlings.phase.DrawingPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class CollectionPhaseTests {

	@Test
	public void testExecuteOnePlayerTwoRandom() {
		Player player = new Player(6, new HandlerFactory(), 0);
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
		Display display = EasyMock.mock(Display.class);
		
		GUI gui = new GUI(promptHandler, display);
		
		ElementBag elementBag = EasyMock.mock(ElementBag.class);
		
		HatchingGround hatchingGround = new HatchingGround(new Deck(Collections.emptyList()));
		
		Phase collectionPhase = new DrawingPhase(players, gui, elementBag, hatchingGround, () -> {}, null);
		collectionPhase.execute();
	}

}

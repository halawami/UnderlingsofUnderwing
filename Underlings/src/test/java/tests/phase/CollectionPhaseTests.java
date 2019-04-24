package tests.phase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Ignore;

import underlings.element.ElementBag;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.handler.HandlerFactory;
import underlings.phase.CollectionPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class CollectionPhaseTests {

	@Ignore
	public void testExecuteOnePlayerTwoRandom() {

		Phase collectionPhase = new CollectionPhase();

		Player player = new Player(6, new HandlerFactory());
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
		Display display = EasyMock.mock(Display.class);
		
		GUI gui = new GUI(promptHandler, display);
		
		ElementBag elementBag = EasyMock.mock(ElementBag.class);
		
		HatchingGround hatchingGround = new HatchingGround(new Deck(Collections.emptyList()));
		
		collectionPhase.execute(players, gui, elementBag, hatchingGround, () -> {});

	}

}

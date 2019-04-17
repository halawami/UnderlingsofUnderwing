package tests.phase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.game.Handler;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.ElementDrawChoice;
import underlings.gui.ElementGiver;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.phase.ElementPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class ElementPhaseTests {

	@Test
	public void testExecuteOnePlayerTwoRandom() {

		Phase elementPhase = new ElementPhase();

		Player player = EasyMock.createMock(Player.class);
		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
		Display display = EasyMock.mock(Display.class);
		GUI gui = new GUI(promptHandler, display);
		ElementBag elementBag = EasyMock.createMock(ElementBag.class);
		HatchingGround hatchingGround = EasyMock.createMock(HatchingGround.class);
		Element element = EasyMock.createMock(Element.class);
		Handler handler = EasyMock.mock(Handler.class);
		Runnable runnable = EasyMock.mock(Runnable.class);

		ElementGiver elementGiver = EasyMock.mock(ElementGiver.class);
		
		List<ElementGiver> elementGivers = new LinkedList<>();
		elementGivers.add(elementGiver);
		elementGivers.add(elementGiver);
		
		ElementDrawChoice elementDrawChoice = EasyMock.mock(ElementDrawChoice.class);
		
		List<ElementDrawChoice> elementDrawChoices = new LinkedList<>();
		elementDrawChoices.add(elementDrawChoice);
		
		EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
		
		EasyMock.expect(promptHandler.promptElementGiver(elementGivers)).andReturn(elementGiver);
		EasyMock.expect(elementGiver.getElementDrawChoices()).andReturn(elementDrawChoices);
		EasyMock.expect(promptHandler.promptElementDrawChoice(elementDrawChoices)).andReturn(elementDrawChoice);
		EasyMock.expect(elementBag.drawRandomElement()).andReturn(element);
		player.addElement(EasyMock.anyObject(Element.class));
		
		EasyMock.expect(promptHandler.promptElementGiver(elementGivers)).andReturn(elementGiver);
		EasyMock.expect(elementGiver.getElementDrawChoices()).andReturn(elementDrawChoices);
		EasyMock.expect(promptHandler.promptElementDrawChoice(elementDrawChoices)).andReturn(elementDrawChoice);
		EasyMock.expect(elementBag.drawRandomElement()).andReturn(element);
		player.addElement(EasyMock.anyObject(Element.class));
		
		runnable.run();
		EasyMock.expectLastCall().times(2);
		
		EasyMock.replay(player, promptHandler, display, elementBag, hatchingGround, handler, elementGiver, elementDrawChoice, runnable);
		
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		elementPhase.execute(players, gui, elementBag, hatchingGround, runnable);
		assertEquals(0, elementGivers.size());
		
		EasyMock.verify(player, promptHandler, display, elementBag, hatchingGround, handler, elementGiver, elementDrawChoice, runnable);

	}

}

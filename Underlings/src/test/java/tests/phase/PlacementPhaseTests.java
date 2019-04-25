package tests.phase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.phase.Phase;
import underlings.phase.PlacementPhase;
import underlings.player.Player;

public class PlacementPhaseTests {

	@SuppressWarnings("unchecked")
	@Test
	public void basicTest() {
		// create players and define actions
		Player player = EasyMock.createMock(Player.class);
		List<Player> players = Arrays.asList(player);

		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getPlayerId()).andReturn(1).anyTimes();

		ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);
		EasyMock.expect(player.getElementSpaceLogic()).andReturn(logic).anyTimes();

		List<Element> playerElements = new ArrayList<Element>();
		Element blue1 = new Element(ElementColor.BLUE);
		playerElements.add(blue1);
		Element blue2 = new Element(ElementColor.BLUE);
		playerElements.add(blue2);
		Element white = new Element(ElementColor.WHITE);
		playerElements.add(white);
		EasyMock.expect(player.getElements()).andReturn(playerElements).anyTimes();

		// create hatchingGround and define actions
		HatchingGround hatchingGround = EasyMock.createMock(HatchingGround.class);
		hatchingGround.cards = new Card[1][1];
		Card card = new Card();
		hatchingGround.cards[0][0] = card;
		ElementSpace redSpace = EasyMock.mock(ElementSpace.class);
		ElementSpace blueSpace = EasyMock.mock(ElementSpace.class);
		ElementSpace greenSpace = EasyMock.mock(ElementSpace.class);
		ElementSpace whiteSpace = EasyMock.mock(ElementSpace.class);
		ElementSpace[] spaces = { redSpace, blueSpace, greenSpace, whiteSpace };
		card.elementSpaces = spaces;

		// create other fields
		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
		Display display = EasyMock.mock(Display.class);
		GUI gui = new GUI(promptHandler, display);
		ElementBag elementBag = EasyMock.createMock(ElementBag.class);
		Runnable runnable = EasyMock.mock(Runnable.class);

		// define expected flow of activity
		EasyMock.expect(logic.getPlayableSpaces(card, EasyMock.anyObject(List.class))).andReturn(Arrays.asList(blueSpace, greenSpace, whiteSpace)).anyTimes();
		EasyMock.expect(promptHandler.promptChoice("Pick a card to place an element on.", Arrays.asList(card), 1)).andReturn(card);
		EasyMock.expect(promptHandler.promptChoice("Pick an element space to place an element on.",Arrays.asList(blueSpace, greenSpace, whiteSpace), 1)).andReturn(blueSpace);
		EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
		EasyMock.expect(promptHandler.promptChoice("Pick an element to place", Arrays.asList(blue1, blue2), 1)).andReturn(blue1);
		blueSpace.addElements(blue1);
		player.removeElement(blue1);
		runnable.run();
		EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
		EasyMock.expect(promptHandler.promptDecision("Would you like to place another element?", 1)).andReturn(false);
		
		// assert expected actions occurred
		EasyMock.replay(player, promptHandler, display, elementBag, hatchingGround, runnable);
		EasyMock.replay(logic, redSpace, blueSpace, greenSpace, whiteSpace);
		Phase phase = new PlacementPhase(players, gui, elementBag, hatchingGround, runnable, null);
		phase.execute();
		EasyMock.verify(player, promptHandler, display, elementBag, hatchingGround, runnable);
		EasyMock.verify(logic, redSpace, blueSpace, greenSpace, whiteSpace);
	}
}

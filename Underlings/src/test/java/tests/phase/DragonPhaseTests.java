package tests.phase;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.phase.DragonPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class DragonPhaseTests {

	@Test
	public void testInit() {
		new DragonPhase(null, null, null, null, null, null);
	}

	@Test
	public void testSetupWithPurple() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> completeEggs = Arrays.asList(card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);

		EasyMock.replay(hatchingGround, bag, player);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		EasyMock.verify(hatchingGround, bag, player);
	}

	@Test
	public void testSetupWithOrange() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.ORANGE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
		List<Card> completeEggs = Arrays.asList(card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.YELLOW);

		EasyMock.replay(hatchingGround, bag, player);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		EasyMock.verify(hatchingGround, bag, player);
	}

	@Test
	public void testEmptySetup() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		List<Card> completeEggs = Arrays.asList();

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);

		EasyMock.replay(hatchingGround, bag, player);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		EasyMock.verify(hatchingGround, bag, player);
	}

	@Test
	public void testMultiSetup() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		Card card1 = new Card();
		ElementSpace[] spaces1 = { new ElementSpace(ElementColor.ORANGE) };
		card1.elementSpaces = spaces1;
		spaces1[0].elements = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);

		Card card2 = new Card();
		ElementSpace[] spaces2 = { new ElementSpace(ElementColor.PURPLE), new ElementSpace(ElementColor.RED) };
		card2.elementSpaces = spaces2;
		spaces2[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		spaces2[1].elements = Arrays.asList(ElementColor.RED);

		List<Card> completeEggs = Arrays.asList(card1, card2);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.YELLOW);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.RED);

		EasyMock.replay(hatchingGround, bag, player);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		EasyMock.verify(hatchingGround, bag, player);
	}

	@Test
	public void testEmptyTurn() {
		Player player = EasyMock.mock(Player.class);

		EasyMock.replay(player);
		Phase phase = new DragonPhase(null, null, null, null, null, null);
		phase.turn(player);
		EasyMock.verify(player);
	}

	@Test
	public void testOneEgg() {
		Player player = EasyMock.mock(Player.class);
		Handler handler = EasyMock.mock(Handler.class);
		Card card = new Card();
		card.handler = handler;
		card.domesticEffects = new Effect[1];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		EasyMock.expect(player.getUnhatchedEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		card.domesticEffects[0].apply(player);
		
		EasyMock.replay(player);
		Phase phase = new DragonPhase(null, null, null, null, null, null);
		phase.turn(player);
		EasyMock.verify(player);
	}
}

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
	public void testOneEgg() {
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
		
		Handler handler = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[1];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		EasyMock.expect(player.getUnhatchedEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		card.domesticEffects[0].apply(player);
		
		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler);
	}
	
	@Test
	public void testTwoEggsSamePlayer() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> completeEggs = Arrays.asList(card, card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		
		Handler handler = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[1];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		EasyMock.expect(player.getUnhatchedEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		card.domesticEffects[0].apply(player);
		handler.moveToState(HandlerState.READY_ROOM);
		card.domesticEffects[0].apply(player);
		
		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler);
	}	
	
	@Test
	public void testOneEggWithTwoEffects() {
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
		
		Handler handler = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[2];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		card.domesticEffects[1] = EasyMock.mock(Effect.class);
		EasyMock.expect(player.getUnhatchedEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		card.domesticEffects[0].apply(player);
		card.domesticEffects[1].apply(player);
		
		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, card.domesticEffects[1]);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, card.domesticEffects[1]);
	}	
	
	@Test
	public void testTwoEggsDifferentPlayers() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player, player2);

		Card card = new Card();
		Card card2 = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		card2.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> completeEggs = Arrays.asList(card, card2);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		
		Handler handler = EasyMock.mock(Handler.class);
		Handler handler2 = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[1];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		card2.handler = handler2;
		card2.domesticEffects = new Effect[1];
		card2.domesticEffects[0] = EasyMock.mock(Effect.class);
		EasyMock.expect(player.getUnhatchedEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
		EasyMock.expect(player2.getUnhatchedEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player2.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player2.getHandlers()).andReturn(Arrays.asList(handler2)).anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		card.domesticEffects[0].apply(player);
		handler2.moveToState(HandlerState.READY_ROOM);
		card2.domesticEffects[0].apply(player2);
		
		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, player2, card2.domesticEffects[0], handler2);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		phase.turn(player2);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, player2, card2.domesticEffects[0], handler2);
	}	
	
	// TODO: add more tests
	// 1. add test for two eggs, where one is not the player's DONE
	// 2. add test for one egg with two effects
	// 3. add test for no eggs
	// 4. add tests where the player has 1 unhatched egg
	// 5. add tests where the player has 2 unhatched eggs
	// 6. add tests where the player has 0 unhatched eggs
}

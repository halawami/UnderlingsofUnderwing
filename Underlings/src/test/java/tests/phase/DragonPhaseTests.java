package tests.phase;

import org.easymock.EasyMock;
import org.junit.Test;
import org.w3c.dom.DOMError;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<Card> eggs = Arrays.asList(card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
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
		List<Card> eggs = Arrays.asList(card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
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

		List<Card> eggs = Arrays.asList();

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);

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

		List<Card> eggs = Arrays.asList(card1, card2);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
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
	public void testOneUnhatchedEgg() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);
		player.hatchedCards = new ArrayList<>();

		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> eggs = Arrays.asList(card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs).anyTimes();
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);

		Handler handler = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[1];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		player.unhatchedCards = new ArrayList<>();
		player.unhatchedCards.add(card);
//		EasyMock.expect(player.getUnhatchedEggs()).andReturn((eggs)).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
//		player.clearUnhatchedEggs();
//		EasyMock.expectLastCall().anyTimes();
//		player.addUnhatchedEggs(eggs.get(0));
//		EasyMock.expectLastCall().anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
		handler.moveToState(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.INCUBATION);
		// TODO: ask Mohammad if this is ok?
		EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).anyTimes();
		card.domesticEffects[0].apply();

		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler);

		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler);
	}

	@Test
	public void testTwoUnhatchedEggsSamePlayer() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		player.hatchedCards = new ArrayList<>();
		List<Player> players = Arrays.asList(player);

		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> eggs = Arrays.asList(card, card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);

		Handler handler = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[1];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		player.unhatchedCards = new ArrayList<>();
		player.unhatchedCards.add(card);
		player.unhatchedCards.add(card);
//		EasyMock.expect(player.getUnhatchedEggs()).andReturn((eggs)).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
//		player.clearUnhatchedEggs();
//		EasyMock.expectLastCall().anyTimes();
//		player.addUnhatchedEggs(eggs.get(0));
//		EasyMock.expectLastCall().anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).anyTimes();
		card.domesticEffects[0].apply();
		EasyMock.expectLastCall().anyTimes();
		handler.moveToState(HandlerState.READY_ROOM);
		handler.moveToState(HandlerState.INCUBATION);
		handler.moveToState(HandlerState.INCUBATION);

		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler);
	}

	@Test
	public void testOneUnhatchedEggWithTwoEffects() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);
		player.hatchedCards = new ArrayList<>();

		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> eggs = Arrays.asList(card);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);

		Handler handler = EasyMock.mock(Handler.class);
		card.handler = handler;
		card.domesticEffects = new Effect[2];
		card.domesticEffects[0] = EasyMock.mock(Effect.class);
		card.domesticEffects[1] = EasyMock.mock(Effect.class);
		player.unhatchedCards = new ArrayList<>();
		player.unhatchedCards.add(card);
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();

		handler.moveToState(HandlerState.READY_ROOM);
		EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).anyTimes();
		EasyMock.expect(card.domesticEffects[1].on(player)).andReturn(card.domesticEffects[1]).anyTimes();
		card.domesticEffects[0].apply();
		card.domesticEffects[1].apply();
		handler.moveToState(HandlerState.INCUBATION);

		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, card.domesticEffects[1]);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, card.domesticEffects[1]);
	}

	@Test
	public void testTwoUnhatchedEggsDifferentPlayers() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player, player2);
		player.hatchedCards = new ArrayList<>();
		player2.hatchedCards = new ArrayList<>();

		Card card = new Card();
		Card card2 = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		card2.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> eggs = Arrays.asList(card, card2);

		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
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
		player.unhatchedCards = new ArrayList<>();
		player2.unhatchedCards = new ArrayList<>();
		player.unhatchedCards.add(card);
		player2.unhatchedCards.add(card2);
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
		EasyMock.expect(player2.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player2.getHandlers()).andReturn(Arrays.asList(handler2)).anyTimes();
		handler.moveToState(HandlerState.READY_ROOM);
		EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).anyTimes();
		card.domesticEffects[0].apply();
		handler2.moveToState(HandlerState.READY_ROOM);
		EasyMock.expect(card2.domesticEffects[0].on(player2)).andReturn(card2.domesticEffects[0]).anyTimes();
		card2.domesticEffects[0].apply();
		handler.moveToState(HandlerState.INCUBATION);
		handler2.moveToState(HandlerState.INCUBATION);
		EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, player2,
				card2.domesticEffects[0], handler2);

		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		phase.turn(player2);
		EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, player2,
				card2.domesticEffects[0], handler2);
	}

	@Test
	public void testNoUnhatchedEggs() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		List<Card> eggs = new ArrayList<>();
		player.unhatchedCards = new ArrayList<>();
		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
		
		EasyMock.replay(hatchingGround, bag, player);

		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player);
	}

	@Test
	public void testOneUncompletedEgg() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);
		
		Card card = new Card();
		player.hatchedCards = new ArrayList<>();
		Handler handler = EasyMock.mock(Handler.class);
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		card.handler = handler;
		card.domesticEffects = new Effect[0];
		player.unhatchedCards = new ArrayList<>();
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card)).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
		handler.moveToState(HandlerState.READY_ROOM);
		EasyMock.expectLastCall().anyTimes();
		handler.moveToState(HandlerState.INCUBATION);
		EasyMock.expectLastCall().anyTimes();

		EasyMock.replay(hatchingGround, bag, player, handler);

		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, handler);
	}

	@Test
	public void testTwoUncompletedEgg() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);

		Card card = new Card();
		Card card2 = new Card();
		player.hatchedCards = new ArrayList<>();
		Handler handler = EasyMock.mock(Handler.class);
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		card2.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		card.handler = handler;
		card.domesticEffects = new Effect[0];
		card2.handler = handler;
		card2.domesticEffects = new Effect[0];
		player.unhatchedCards = new ArrayList<>();
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card)).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
		handler.moveToState(HandlerState.READY_ROOM);
		EasyMock.expectLastCall().anyTimes();
		handler.moveToState(HandlerState.INCUBATION);
		EasyMock.expectLastCall().anyTimes();

		EasyMock.replay(hatchingGround, bag, player, handler);

		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, handler);
	}

	@Test
	public void testNoUncompletedEgg() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);
		player.hatchedCards = new ArrayList<>();
		Handler handler = EasyMock.mock(Handler.class);
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		player.unhatchedCards = new ArrayList<>();
		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList()).anyTimes();
		EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
		EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
		handler.moveToState(HandlerState.READY_ROOM);
		EasyMock.expectLastCall().anyTimes();
		handler.moveToState(HandlerState.INCUBATION);
		EasyMock.expectLastCall().anyTimes();

		EasyMock.replay(hatchingGround, bag, player, handler);

		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		phase.turn(player);
		EasyMock.verify(hatchingGround, bag, player, handler);
	}
}

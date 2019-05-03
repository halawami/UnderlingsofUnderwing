package tests.phase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.phase.DragonPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class DragonPhaseTests {

    private HatchingGround hatchingGround;
    private ElementBag bag;
    private Player player;
    private List<Player> players;
    private Card card;
    private ElementSpace[] spaces;
    private List<Card> eggs;
    Handler handler;

    @Before
    public void init() {
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.bag = EasyMock.mock(ElementBag.class);
        this.player = EasyMock.mock(Player.class);
        this.handler = EasyMock.mock(Handler.class);
        this.card = new Card();
        this.player.hatchedCards = new ArrayList<>();
        this.player.unhatchedCards = new ArrayList<>();
        this.players = Arrays.asList(player);
        this.card.handler = this.handler;
        this.card.domesticEffects = new Effect[1];
        this.card.domesticEffects[0] = EasyMock.mock(Effect.class);
        this.spaces = new ElementSpace[1];
        this.spaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces = this.spaces;
        this.spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
        this.eggs = Arrays.asList(card);

        EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
        EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
    }

    @Test
    public void testInit() {
        new DragonPhase(null, null, null, null, null, null);
    }

    @Test
    public void testSetupWithPurple() {
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
        this.spaces[0] = new ElementSpace(ElementColor.ORANGE);
        this.card.elementSpaces = this.spaces;
        this.spaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
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
        eggs = Arrays.asList();
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);

        EasyMock.replay(hatchingGround, bag, player);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
        phase.setup();
        EasyMock.verify(hatchingGround, bag, player);
    }

    @Test
    public void testMultiSetup() {
        this.spaces[0] = new ElementSpace(ElementColor.ORANGE);
        this.card.elementSpaces = this.spaces;
        this.spaces[0].elements = Arrays.asList(ElementColor.YELLOW, ElementColor.RED);
        Card card2 = new Card();
        ElementSpace[] spaces2 = {new ElementSpace(ElementColor.PURPLE), new ElementSpace(ElementColor.RED)};
        card2.elementSpaces = spaces2;
        spaces2[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
        spaces2[1].elements = Arrays.asList(ElementColor.RED);

        List<Card> eggs = Arrays.asList(card, card2);

        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(eggs);
        bag.putElement(ElementColor.RED);
        EasyMock.expectLastCall().times(3);
        bag.putElement(ElementColor.YELLOW);
        bag.putElement(ElementColor.BLUE);

        EasyMock.replay(hatchingGround, bag, player);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
        phase.setup();
        EasyMock.verify(hatchingGround, bag, player);
    }

    @Test
    public void testOneUnhatchedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 1;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        player.unhatchedCards.add(card);
        handler.moveToState(HandlerState.READY_ROOM);
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(bag)).andReturn(card.domesticEffects[0]);
        card.domesticEffects[0].apply();
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler);
    }

    @Test
    public void testTwoUnhatchedEggsSamePlayer() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 1;
        card.name = "tempName";
        final String message = card.name + " has been applied";
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        player.unhatchedCards.add(card);
        player.unhatchedCards.add(card);
        handler.moveToState(HandlerState.READY_ROOM);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).times(2);
        EasyMock.expect(card.domesticEffects[0].on(bag)).andReturn(card.domesticEffects[0]).times(2);
        card.domesticEffects[0].apply();
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler);
    }

    @Test
    public void testOneUnhatchedEggWithTwoEffects() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 1;
        card.name = "tempName";
        final String message = card.name + " has been applied";
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        card.domesticEffects = new Effect[2];
        card.domesticEffects[0] = EasyMock.mock(Effect.class);
        card.domesticEffects[1] = EasyMock.mock(Effect.class);
        player.unhatchedCards.add(card);

        handler.moveToState(HandlerState.READY_ROOM);
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[1].on(player)).andReturn(card.domesticEffects[1]);
        EasyMock.expect(card.domesticEffects[0].on(bag)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[1].on(bag)).andReturn(card.domesticEffects[1]);
        card.domesticEffects[0].apply();
        card.domesticEffects[1].apply();
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, card.domesticEffects[1]);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, card.domesticEffects[1]);
    }

    @Test
    public void testTwoUnhatchedEggsDifferentPlayers() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 0;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        Player player2 = EasyMock.mock(Player.class);
        players = Arrays.asList(player, player2);
        player2.hatchedCards = new ArrayList<>();
        Card card2 = new Card();
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE)};
        card2.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        Handler handler2 = EasyMock.mock(Handler.class);
        card2.handler = handler2;
        card2.domesticEffects = new Effect[1];
        card2.domesticEffects[0] = EasyMock.mock(Effect.class);
        player2.unhatchedCards = new ArrayList<>();
        card2.name = "tempName";
        final int player2Id = 0;
        final String message2 = card2.name + " is going to incubation state";
        player.unhatchedCards.add(card);
        player2.unhatchedCards.add(card2);
        EasyMock.expect(player2.getHandlerCount()).andReturn(1).anyTimes();
        EasyMock.expect(player2.getHandlers()).andReturn(Arrays.asList(handler2)).anyTimes();
        handler.moveToState(HandlerState.READY_ROOM);
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(bag)).andReturn(card.domesticEffects[0]);
        card.domesticEffects[0].apply();
        handler2.moveToState(HandlerState.READY_ROOM);
        EasyMock.expect(card2.domesticEffects[0].on(player2)).andReturn(card2.domesticEffects[0]);
        EasyMock.expect(card2.domesticEffects[0].on(bag)).andReturn(card2.domesticEffects[0]);
        card2.domesticEffects[0].apply();
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);
        EasyMock.expect(player2.getPlayerId()).andReturn(player2Id).anyTimes();
        gui.notifyAction(player2Id, message2);

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, player2,
                card2.domesticEffects[0], handler2);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        phase.turn(player2);
        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, player2,
                card2.domesticEffects[0], handler2);
    }

    @Test
    public void testNoUnhatchedEggs() {
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        EasyMock.replay(hatchingGround, bag, player);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player);
    }

    @Test
    public void testOneUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 0;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        card.domesticEffects = new Effect[0];
        bag.putElement(ElementColor.BLUE);
        bag.putElement(ElementColor.RED);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card));
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);
        handler.moveToState(HandlerState.INCUBATION);

        EasyMock.replay(hatchingGround, bag, player, handler, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler, gui);
    }

    @Test
    public void testTwoUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 1;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        Card card2 = new Card();
        card2.elementSpaces = spaces;
        card2.handler = handler;
        card2.domesticEffects = new Effect[0];
        card2.name = "tempName";
        bag.putElement(ElementColor.BLUE);
        EasyMock.expectLastCall().times(2);
        bag.putElement(ElementColor.RED);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card, card2));
        handler.moveToState(HandlerState.INCUBATION);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(hatchingGround, bag, player, handler, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler, gui);
    }

    @Test
    public void testTwoUncompletedDifferentPlayerEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 0;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        Player player2 = EasyMock.mock(Player.class);
        players = Arrays.asList(player, player2);
        player2.hatchedCards = new ArrayList<>();
        Card card2 = new Card();
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE)};
        card2.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card, card2));
        Handler handler2 = EasyMock.mock(Handler.class);
        card2.handler = handler2;
        card2.domesticEffects = new Effect[1];
        card2.domesticEffects[0] = EasyMock.mock(Effect.class);
        card2.name = "tempName";
        player2.unhatchedCards = new ArrayList<>();
        final int player2Id = 0;
        final String message2 = card2.name + " is going to incubation state";
        EasyMock.expect(player2.getHandlerCount()).andReturn(1).anyTimes();
        EasyMock.expect(player2.getHandlers()).andReturn(Arrays.asList(handler2)).anyTimes();
        bag.putElement(ElementColor.BLUE);
        EasyMock.expectLastCall().times(2);
        bag.putElement(ElementColor.RED);
        EasyMock.expectLastCall().times(2);
        handler.moveToState(HandlerState.INCUBATION);
        handler2.moveToState(HandlerState.INCUBATION);
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        gui.notifyAction(playerId, message);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(player2.getPlayerId()).andReturn(player2Id).anyTimes();
        gui.notifyAction(player2Id, message2);
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, player2,
                card2.domesticEffects[0], handler2, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        phase.turn(player2);
        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, player2,
                card2.domesticEffects[0], handler2, gui);
    }

    @Test
    public void testNoUncompletedEgg() {
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        EasyMock.replay(hatchingGround, bag, player, handler);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler);
    }
}

package underlings.phase;

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
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
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
        final int playerId = 1;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        player.unhatchedCards.add(card);
        EasyMock.expect(card.domesticEffects[0].on(bag)).andReturn(card.domesticEffects[0]).anyTimes();
        EasyMock.expect(card.domesticEffects[0].on(hatchingGround)).andReturn(card.domesticEffects[0]).anyTimes();
        player.elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        EasyMock.expect(card.domesticEffects[0].on(player.elementSpaceLogic)).andReturn(card.domesticEffects[0])
                .anyTimes();
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).anyTimes();
        final Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.domesticEffects[0].on(gui)).andReturn(card.domesticEffects[0]).anyTimes();
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
    public void testTwoUnhatchedEgg() {
        final int playerId = 1;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        player.unhatchedCards.add(card);
        player.unhatchedCards.add(card);
        EasyMock.expect(card.domesticEffects[0].on(bag)).andReturn(card.domesticEffects[0]).anyTimes();
        EasyMock.expect(card.domesticEffects[0].on(hatchingGround)).andReturn(card.domesticEffects[0]).anyTimes();
        player.elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        EasyMock.expect(card.domesticEffects[0].on(player.elementSpaceLogic)).andReturn(card.domesticEffects[0])
                .anyTimes();
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]).anyTimes();
        final Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.domesticEffects[0].on(gui)).andReturn(card.domesticEffects[0]).anyTimes();
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
        EasyMock.expect(player.hasCard(card)).andReturn(true);
        gui.notifyAction(playerId, message);

        EasyMock.replay(hatchingGround, bag, player, handler, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler, gui);
    }

    @Test
    public void testTwoUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        final int playerId = 0;
        card.name = "tempName";
        final String message = card.name + " is going to incubation state";
        card.domesticEffects = new Effect[0];
        bag.putElement(ElementColor.BLUE);
        EasyMock.expectLastCall().times(2);
        bag.putElement(ElementColor.RED);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card, card));
        EasyMock.expect(player.getPlayerId()).andReturn(playerId).anyTimes();
        EasyMock.expect(player.hasCard(card)).andReturn(true).times(2);
        gui.notifyAction(playerId, message);
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(hatchingGround, bag, player, handler, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler, gui);
    }

    @Test
    public void testOneUncompletedEggNotThePlayerEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        card.domesticEffects = new Effect[0];
        bag.putElement(ElementColor.BLUE);
        bag.putElement(ElementColor.RED);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card));
        EasyMock.expect(player.hasCard(card)).andReturn(false);

        EasyMock.replay(hatchingGround, bag, player, handler, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler, gui);
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

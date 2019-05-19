package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.ElementSpaceUtilities;
import underlings.utilities.LocaleUtilities;

public class DragonPhaseTests extends MockTest {

    @Before
    public void init() {
        this.hatchingGround = this.mock(HatchingGround.class);
        this.elementBag = this.mock(ElementBag.class);
        this.player = this.mock(Player.class);
        this.handler = this.mock(Handler.class);
        this.player.elementSpaceLogic = this.mock(ElementSpaceUtilities.class);

        this.card = new Card();
        this.player.hatchedCards = new ArrayList<>();
        this.player.unhatchedCards = new HashMap<>();

        this.players = Arrays.asList(this.player);
        this.card.handler = this.handler;
        this.card.domesticEffects = new Effect[1];
        this.card.domesticEffects[0] = this.mock(Effect.class);
        this.elementSpaces = new ElementSpace[1];
        this.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces = this.elementSpaces;
        this.eggHatchingLogic = this.mock(EggHatchingUtilities.class);
        this.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.BLUE), new Element(ElementColor.RED));
        this.gui = this.mock(Gui.class);

        EasyMock.expect(this.player.getHandlerCount()).andReturn(1).anyTimes();
        this.player.handlers = Arrays.asList(this.handler);
    }

    @Test
    public void testOneUnhatchedEgg() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        this.player.unhatchedCards.put(this.card, 1);
        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        EasyMock.expect(this.player.getId()).andReturn(1).anyTimes();

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, null, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        assertEquals(1, this.player.unhatchedCards.size());
        phase.turn(this.player);
        phase.teardown();
        assertEquals(0, this.player.unhatchedCards.size());
        assertTrue(phase.isPhaseComplete());

    }

    @Test
    public void testTwoUnhatchedEgg() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        Card card2 = new Card();
        card2.domesticEffects = new Effect[] {EasyMock.mock(Effect.class)};
        this.player.unhatchedCards.put(this.card, 1);
        this.player.unhatchedCards.put(card2, 1);
        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        this.eggHatchingLogic.hatchEgg(card2, this.player);
        EasyMock.expect(this.player.getId()).andReturn(1).anyTimes();

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, null, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        assertEquals(2, this.player.unhatchedCards.size());
        phase.turn(this.player);
        phase.teardown();
        assertEquals(0, this.player.unhatchedCards.size());
        assertTrue(phase.isPhaseComplete());
    }

    @Test
    public void testNoUnhatchedEggs() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, null, this.elementBag, this.hatchingGround, null, null, null);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();
        assertTrue(phase.isPhaseComplete());
    }

    @Test
    public void testOneUncompletedEgg() {
        this.player = new Player(2, new HandlerFactory(), 0);
        this.card.handler = this.player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleUtilities.format("incubation_state", this.card.name);
        this.card.domesticEffects = new Effect[0];
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.gui.notifyAction(this.player.getId(), message);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();
        assertTrue(phase.isPhaseComplete());
    }

    @Test
    public void testTwoUncompletedEgg() {
        this.player = new Player(2, new HandlerFactory(), 0);
        this.card.handler = this.player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleUtilities.format("incubation_state", this.card.name);
        this.card.domesticEffects = new Effect[0];
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs())
                .andReturn(Arrays.asList(this.card, this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        EasyMock.expectLastCall().times(2);
        this.gui.notifyAction(this.player.getId(), message);
        EasyMock.expectLastCall().times(2);
        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();
        assertTrue(phase.isPhaseComplete());
    }

    @Test
    public void testOneUncompletedEggNotThePlayerEgg() {
        this.card.domesticEffects = new Effect[0];
        this.player = new Player(2, new HandlerFactory(), 0);
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();
        assertTrue(phase.isPhaseComplete());
    }

    @Test
    public void testNoUncompletedEgg() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, null, this.elementBag, this.hatchingGround, null, null, null);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();
        assertTrue(phase.isPhaseComplete());
    }

    @Test
    public void testHatchingTimeZero() {
        this.player.hatchingTime = 0;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);

        this.eggHatchingLogic.hatchEgg(this.card, this.player);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, null, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();

        assertTrue(phase.isPhaseComplete());
        assertEquals(0, this.player.unhatchedCards.size());
        assertEquals(1, this.player.hatchingTime);
    }

    @Test
    public void testHatchingTimeZeroTwoEggs() {
        this.player = new Player(2, new HandlerFactory(), 0);
        this.player.hatchingTime = 0;
        this.card.handler = this.player.handlers.get(0);
        Card card2 = new Card();
        card2.name = "tempName";
        final String message = LocaleUtilities.format("incubation_state", card2.name);
        card2.handler = this.player.handlers.get(1);
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card, card2));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        this.eggHatchingLogic.returnElementsToBag(card2);
        this.gui.notifyAction(this.player.getId(), message);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        phase.teardown();

        assertEquals(1, this.player.hatchingTime);
        assertEquals(1, this.player.unhatchedCards.size());
        assertTrue(phase.isPhaseComplete());
        assertEquals(HandlerState.INCUBATION, card2.handler.getState());
    }

    @Test
    public void testHatchLateEgg() {
        Player player = new Player(6, new HandlerFactory(), 1);
        this.card.handler = player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleUtilities.format("incubation_state", this.card.name);
        this.hatchingGround.lateHatching = true;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.gui.notifyAction(player.getId(), message);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        phase.teardown();
        assertEquals(new Integer(2), player.unhatchedCards.get(this.card));
    }

    @Test
    public void testThreeRoundsLateEgg() {
        Player player = new Player(6, new HandlerFactory(), 1);
        this.card.handler = player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleUtilities.format("incubation_state", this.card.name);
        this.hatchingGround.lateHatching = true;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        EasyMock.expectLastCall().times(2);
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.gui.notifyAction(player.getId(), message);
        this.eggHatchingLogic.hatchEgg(this.card, player);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        phase.setup();
        phase.turn(player);
        phase.setup();
        phase.turn(player);
        phase.teardown();
    }

    @Test
    public void testTwoRoundsLateEgg() {
        Player player = new Player(6, new HandlerFactory(), 1);
        this.card.handler = player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleUtilities.format("incubation_state", this.card.name);
        this.hatchingGround.lateHatching = true;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.gui.notifyAction(player.getId(), message);

        this.replayAll();

        DragonPhase phase = new DragonPhase(this.players, this.gui, this.elementBag, this.hatchingGround, null, null,
                this.eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        phase.setup();
        phase.turn(player);
        phase.teardown();
    }


}

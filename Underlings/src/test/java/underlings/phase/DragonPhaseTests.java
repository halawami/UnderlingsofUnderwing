package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class DragonPhaseTests {

    private HatchingGround hatchingGround;
    private ElementBag bag;
    private Player player;
    private List<Player> players;
    private Card card;
    private ElementSpace[] spaces;
    private EggHatchingLogic eggHatchingLogic;
    Handler handler;

    @Before
    public void init() {
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.bag = EasyMock.mock(ElementBag.class);
        this.player = EasyMock.mock(Player.class);
        this.handler = EasyMock.mock(Handler.class);
        this.card = new Card();
        this.player.hatchedCards = new ArrayList<>();
        this.player.unhatchedCards = new HashMap<>();
        this.player.elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        this.players = Arrays.asList(this.player);
        this.card.handler = this.handler;
        this.card.domesticEffects = new Effect[1];
        this.card.domesticEffects[0] = EasyMock.mock(Effect.class);
        this.spaces = new ElementSpace[1];
        this.spaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces = this.spaces;
        this.eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        this.spaces[0].elements = Arrays.asList(new Element(ElementColor.BLUE), new Element(ElementColor.RED));

        EasyMock.expect(this.player.getHandlerCount()).andReturn(1).anyTimes();
        this.player.handlers = Arrays.asList(this.handler);
    }

    @Test
    public void testInit() {
        new DragonPhase(null, null, null, null, null, null, null);
    }

    @Test
    public void testOneUnhatchedEgg() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        this.player.unhatchedCards.put(this.card, 1);
        this.eggHatchingLogic.hatchEgg(this.card, false, this.player);
        EasyMock.expect(this.player.getId()).andReturn(1).anyTimes();

        EasyMock.replay(this.hatchingGround, this.bag, this.player, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, null, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        assertEquals(1, this.player.unhatchedCards.size());
        phase.turn(this.player);
        assertEquals(0, this.player.unhatchedCards.size());
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.player, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
    }

    @Test
    public void testTwoUnhatchedEgg() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        Card card2 = new Card();
        card2.domesticEffects = new Effect[] {EasyMock.mock(Effect.class)};
        this.player.unhatchedCards.put(this.card, 1);
        this.player.unhatchedCards.put(card2, 1);
        this.eggHatchingLogic.hatchEgg(this.card, false, this.player);
        this.eggHatchingLogic.hatchEgg(card2, false, this.player);
        EasyMock.expect(this.player.getId()).andReturn(1).anyTimes();

        EasyMock.replay(this.hatchingGround, this.bag, this.player, this.card.domesticEffects[0],
                card2.domesticEffects[0], this.handler, this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, null, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        assertEquals(2, this.player.unhatchedCards.size());
        phase.turn(this.player);
        assertEquals(0, this.player.unhatchedCards.size());
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.player, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
    }

    @Test
    public void testNoUnhatchedEggs() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        EasyMock.replay(this.hatchingGround, this.bag, this.player);

        Phase phase = new DragonPhase(this.players, null, this.bag, this.hatchingGround, null, null, null);
        phase.setup();
        phase.turn(this.player);
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.player);
    }

    @Test
    public void testOneUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        this.player = new Player(2, new HandlerFactory(), 0);
        this.card.handler = this.player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", this.card.name);
        this.card.domesticEffects = new Effect[0];
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        gui.notifyAction(this.player.getId(), message);

        EasyMock.replay(this.hatchingGround, this.bag, this.handler, gui, this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.handler, gui, this.eggHatchingLogic);
    }

    @Test
    public void testTwoUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        this.player = new Player(2, new HandlerFactory(), 0);
        this.card.handler = this.player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", this.card.name);
        this.card.domesticEffects = new Effect[0];
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs())
                .andReturn(Arrays.asList(this.card, this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        EasyMock.expectLastCall().times(2);
        gui.notifyAction(this.player.getId(), message);
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(this.hatchingGround, this.bag, this.handler, gui, this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.handler, gui, this.eggHatchingLogic);
    }

    @Test
    public void testOneUncompletedEggNotThePlayerEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        this.card.domesticEffects = new Effect[0];
        this.player = new Player(2, new HandlerFactory(), 0);
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);

        EasyMock.replay(this.hatchingGround, this.bag, this.handler, gui);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.handler, gui);
    }

    @Test
    public void testNoUncompletedEgg() {
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        EasyMock.replay(this.hatchingGround, this.bag, this.player, this.handler);

        Phase phase = new DragonPhase(this.players, null, this.bag, this.hatchingGround, null, null, null);
        phase.setup();
        phase.turn(this.player);
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.player, this.handler);
    }

    @Test
    public void testHatchingTimeZero() {
        this.player.hatchingTime = 0;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);

        this.eggHatchingLogic.hatchEgg(this.card, false, this.player);
        EasyMock.replay(this.hatchingGround, this.bag, this.player, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, null, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        assertTrue(phase.isPhaseComplete());
        EasyMock.verify(this.hatchingGround, this.bag, this.player, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
        assertEquals(0, this.player.unhatchedCards.size());
        assertEquals(1, this.player.hatchingTime);
    }

    @Test
    public void testHatchingTimeZeroTwoEggs() {
        final Gui gui = EasyMock.mock(Gui.class);
        this.player = new Player(2, new HandlerFactory(), 0);
        this.player.hatchingTime = 0;
        this.card.handler = this.player.handlers.get(0);
        Card card2 = new Card();
        card2.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", card2.name);
        card2.handler = this.player.handlers.get(1);
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card, card2));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.eggHatchingLogic.hatchEgg(this.card, false, this.player);
        this.eggHatchingLogic.returnElementsToBag(card2);
        gui.notifyAction(this.player.getId(), message);

        EasyMock.replay(this.hatchingGround, gui, this.bag, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(this.player);
        EasyMock.verify(this.hatchingGround, this.bag, gui, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
        assertEquals(1, this.player.hatchingTime);
        assertEquals(1, this.player.unhatchedCards.size());
        assertTrue(phase.isPhaseComplete());
        assertEquals(HandlerState.INCUBATION, card2.handler.getState());
    }

    @Test
    public void testHatchLateEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        Player player = new Player(6, new HandlerFactory(), 1);
        this.card.handler = player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", this.card.name);
        this.hatchingGround.lateHatching = true;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        gui.notifyAction(player.getId(), message);

        EasyMock.replay(this.hatchingGround, gui, this.bag, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        assertEquals(new Integer(2), player.unhatchedCards.get(this.card));
        EasyMock.verify(this.hatchingGround, this.bag, gui, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
    }

    @Test
    public void testThreeRoundsLateEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        Player player = new Player(6, new HandlerFactory(), 1);
        this.card.handler = player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", this.card.name);
        this.hatchingGround.lateHatching = true;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        EasyMock.expectLastCall().times(2);
        this.eggHatchingLogic.returnElementsToBag(this.card);
        gui.notifyAction(player.getId(), message);
        this.eggHatchingLogic.hatchEgg(this.card, false, player);

        EasyMock.replay(this.hatchingGround, gui, this.bag, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        phase.setup();
        phase.turn(player);
        phase.setup();
        phase.turn(player);

        EasyMock.verify(this.hatchingGround, this.bag, gui, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
    }

    @Test
    public void testTwoRoundsLateEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        Player player = new Player(6, new HandlerFactory(), 1);
        this.card.handler = player.handlers.get(0);
        this.card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", this.card.name);
        this.hatchingGround.lateHatching = true;
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(this.card));
        EasyMock.expect(this.hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        this.eggHatchingLogic.returnElementsToBag(this.card);
        gui.notifyAction(player.getId(), message);

        EasyMock.replay(this.hatchingGround, gui, this.bag, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);

        Phase phase =
                new DragonPhase(this.players, gui, this.bag, this.hatchingGround, null, null, this.eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        phase.setup();
        phase.turn(player);

        EasyMock.verify(this.hatchingGround, this.bag, gui, this.card.domesticEffects[0], this.handler,
                this.eggHatchingLogic);
    }

}

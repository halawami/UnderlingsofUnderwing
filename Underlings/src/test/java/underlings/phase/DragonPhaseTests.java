package underlings.phase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.player.unhatchedCards = new ArrayList<>();
        this.player.elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        this.players = Arrays.asList(player);
        this.card.handler = this.handler;
        this.card.domesticEffects = new Effect[1];
        this.card.domesticEffects[0] = EasyMock.mock(Effect.class);
        this.spaces = new ElementSpace[1];
        this.spaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces = this.spaces;
        this.eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        this.spaces[0].elements = Arrays.asList(new Element(ElementColor.BLUE), new Element(ElementColor.RED));

        EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
        EasyMock.expect(player.getHandlers()).andReturn(Arrays.asList(handler)).anyTimes();
    }

    @Test
    public void testInit() {
        new DragonPhase(null, null, null, null, null, null, null);
    }

    @Test
    public void testOneUnhatchedEgg() {
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        player.unhatchedCards.add(card);
        eggHatchingLogic.hatchEgg(card, false, player);
        EasyMock.expect(player.getPlayerId()).andReturn(1).anyTimes();

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, eggHatchingLogic);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        assertEquals(1, player.unhatchedCards.size());
        phase.turn(player);
        assertEquals(0, player.unhatchedCards.size());
        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, eggHatchingLogic);
    }

    @Test
    public void testTwoUnhatchedEgg() {
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        player.unhatchedCards.add(card);
        player.unhatchedCards.add(card);
        eggHatchingLogic.hatchEgg(card, false, player);
        EasyMock.expectLastCall().times(2);
        EasyMock.expect(player.getPlayerId()).andReturn(1).anyTimes();

        EasyMock.replay(hatchingGround, bag, player, card.domesticEffects[0], handler, eggHatchingLogic);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        assertEquals(2, player.unhatchedCards.size());
        phase.turn(player);
        assertEquals(0, player.unhatchedCards.size());

        EasyMock.verify(hatchingGround, bag, player, card.domesticEffects[0], handler, eggHatchingLogic);
    }

    @Test
    public void testNoUnhatchedEggs() {
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        EasyMock.replay(hatchingGround, bag, player);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player);
    }

    @Test
    public void testOneUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        player = new Player(2, new HandlerFactory(), 0);
        card.handler = player.getHandlers().get(0);
        card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", card.name);
        card.domesticEffects = new Effect[0];
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card));
        eggHatchingLogic.returnElementsToBag(card);
        gui.notifyAction(player.getPlayerId(), message);

        EasyMock.replay(hatchingGround, bag, handler, gui, eggHatchingLogic);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, handler, gui, eggHatchingLogic);
    }

    @Test
    public void testTwoUncompletedEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        player = new Player(2, new HandlerFactory(), 0);
        card.handler = player.getHandlers().get(0);
        card.name = "tempName";
        final String message = LocaleWrap.format("incubation_state", card.name);
        card.domesticEffects = new Effect[0];
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card, card));
        eggHatchingLogic.returnElementsToBag(card);
        EasyMock.expectLastCall().times(2);
        gui.notifyAction(player.getPlayerId(), message);
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(hatchingGround, bag, handler, gui, eggHatchingLogic);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, handler, gui, eggHatchingLogic);
    }

    @Test
    public void testOneUncompletedEggNotThePlayerEgg() {
        final Gui gui = EasyMock.mock(Gui.class);
        card.domesticEffects = new Effect[0];
        player = new Player(2, new HandlerFactory(), 0);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card));
        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.replay(hatchingGround, bag, handler, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, handler, gui);
    }

    @Test
    public void testNoUncompletedEgg() {
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());

        EasyMock.replay(hatchingGround, bag, player, handler);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, handler);
    }

    @Test
    public void testHatchingTimeZero() {
        final Gui gui = EasyMock.mock(Gui.class);
        player = new Player(2, new HandlerFactory(), 0);
        card.handler = player.getHandlers().get(0);
        player.hatchingTime = 0;
        card.name = "tempName";
        final String message = LocaleWrap.format("effect_applied", card.domesticEffects[0].toString());
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card));
        eggHatchingLogic.hatchEgg(card, false, player);
        eggHatchingLogic.returnElementsToBag(card);
        gui.notifyAction(player.getPlayerId(), message);

        EasyMock.replay(hatchingGround, bag, card.domesticEffects[0], handler, eggHatchingLogic, gui);

        Phase phase = new DragonPhase(players, gui, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, card.domesticEffects[0], handler, eggHatchingLogic, gui);
        assertEquals(0, player.unhatchedCards.size());
        assertEquals(1, player.hatchedCards.size());
    }
}

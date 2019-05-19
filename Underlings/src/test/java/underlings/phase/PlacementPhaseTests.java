package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.gui.YesNoChoice;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;
import underlings.utilities.PlacementUtilities;

public class PlacementPhaseTests extends MockTest {

    private PlacementUtilities placementUtilities;

    @Before
    public void init() {
        this.player = this.mock(Player.class);
        this.players = Arrays.asList(this.player);
        this.deck = this.mock(Deck.class);
        this.elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        this.gui = this.mock(Gui.class);
        this.placementUtilities = this.mock(PlacementUtilities.class);
        this.hatchingGround = this.mock(HatchingGround.class);
        this.hatchingGround.logic = this.mock(ElementSpaceLogic.class);
    }

    public <T> Object getField(Class<T> fieldClass, PlacementPhase phase, String fieldName)
            throws Exception, SecurityException {
        Field field = fieldClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(phase);
    }

    @SuppressWarnings("rawtypes")
    public Method getMethod(PlacementPhase phase, String methodName, Class... params) throws Exception {
        Method method = phase.getClass().getDeclaredMethod(methodName, params);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void testSetup() throws Exception {
        EasyMock.expect(this.player.getHandlerCount()).andReturn(2).anyTimes();

        this.replayAll();
        PlacementPhase phase = new PlacementPhase(this.players, null, null, null, null, null);
        phase.setup();


        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) this.getField(phase.getClass(), phase, "turnCounts");
        assertEquals(new Integer(2), map.get(this.player));
    }

    @Test
    public void testCheckTurnTwoHandlers() throws Exception {
        EasyMock.expect(this.player.getHandlerCount()).andReturn(2).anyTimes();

        this.replayAll();
        PlacementPhase phase = new PlacementPhase(this.players, null, null, null, null, null);
        phase.setup();
        Method turnCountMethod = this.getMethod(phase, "checkAndDecrementTurnCount", Player.class);
        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) this.getField(phase.getClass(), phase, "turnCounts");
        assertTrue((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(1), map.get(this.player));
        assertTrue((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(0), map.get(this.player));
        assertFalse((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(0), map.get(this.player));

    }

    @Test
    public void testCheckTurnThreeHandlers() throws Exception {
        EasyMock.expect(this.player.getHandlerCount()).andReturn(3).anyTimes();

        this.replayAll();
        PlacementPhase phase = new PlacementPhase(this.players, null, null, null, null, null);
        phase.setup();
        Method turnCountMethod = this.getMethod(phase, "checkAndDecrementTurnCount", Player.class);
        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) this.getField(phase.getClass(), phase, "turnCounts");
        assertTrue((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(2), map.get(this.player));
        assertTrue((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(1), map.get(this.player));
        assertTrue((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(0), map.get(this.player));
        assertFalse((boolean) (turnCountMethod.invoke(phase, this.player)));
        assertEquals(new Integer(0), map.get(this.player));

    }

    @Test
    public void testCheckGameover() throws Exception {
        EasyMock.expect(this.elementSpaceLogic.isComplete(EasyMock.anyObject(Card.class))).andReturn(false).anyTimes();
        EasyMock.expect(this.player.getHandlerCount()).andReturn(3).anyTimes();

        Card card = new Card();
        card.handler = new Handler(HandlerState.CARD);

        EasyMock.expect(this.deck.draw()).andReturn(card);
        HatchingGround hatchingGround = new HatchingGround(this.deck, this.elementSpaceLogic);

        this.replayAll();
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        PlacementPhase phase = new PlacementPhase(this.players, null, hatchingGround, null, null, null);
        phase.setup();
        Method gameoverMethod = this.getMethod(phase, "checkGameover");
        gameoverMethod.invoke(phase);


        assertFalse((boolean) this.getField(Phase.class, phase, "gameComplete"));
        assertFalse((boolean) this.getField(Phase.class, phase, "phaseComplete"));
    }

    @Test
    public void testCheckGameoverNoCards() throws Exception {
        EasyMock.expect(this.player.getHandlerCount()).andReturn(3).anyTimes();
        HatchingGround hatchingGround = new HatchingGround(null, null);

        this.replayAll();
        PlacementPhase phase = new PlacementPhase(this.players, null, hatchingGround, null, null, null);
        phase.setup();
        Method gameoverMethod = this.getMethod(phase, "checkGameover");
        gameoverMethod.invoke(phase);

        assertTrue((boolean) this.getField(Phase.class, phase, "gameComplete"));
        assertTrue((boolean) this.getField(Phase.class, phase, "phaseComplete"));
    }

    @Test
    public void testCheckGameoverLost() throws Exception {
        EasyMock.expect(this.elementSpaceLogic.isComplete(EasyMock.anyObject(Card.class))).andReturn(false).anyTimes();
        EasyMock.expect(this.player.getHandlerCount()).andReturn(3).anyTimes();

        Card card = new Card();
        card.handler = WildHandler.getInstance();
        card.wildEffects = new Effect[0];

        EasyMock.expect(this.deck.draw()).andReturn(card);
        HatchingGround hatchingGround = new HatchingGround(this.deck, this.elementSpaceLogic);

        this.replayAll();
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        PlacementPhase phase = new PlacementPhase(this.players, null, hatchingGround, null, null, null);
        phase.setup();
        Method gameoverMethod = this.getMethod(phase, "checkGameover");
        gameoverMethod.invoke(phase);


        assertTrue((boolean) this.getField(Phase.class, phase, "gameComplete"));
        assertTrue((boolean) this.getField(Phase.class, phase, "phaseComplete"));
    }

    @Test
    public void testMoreMovesNoChoices() {
        EasyMock.expect(this.gui.promptChoice(LocaleWrap.get("gui_more_moves"), YesNoChoice.getChoices(), 0))
                .andReturn(YesNoChoice.NO);

        this.replayAll();

        boolean result =
                this.gui.promptChoice(LocaleWrap.get("gui_more_moves"), YesNoChoice.getChoices(), 0).booleanValue;

        assertFalse(result);
    }

    @Test
    public void testMoreMovesTrue() {
        EasyMock.expect(this.gui.promptChoice(LocaleWrap.get("gui_more_moves"), YesNoChoice.getChoices(), 0))
                .andReturn(YesNoChoice.YES);

        this.replayAll();

        boolean result =
                this.gui.promptChoice(LocaleWrap.get("gui_more_moves"), YesNoChoice.getChoices(), 0).booleanValue;


        assertTrue(result);
    }

    @Test
    public void testTurnOver() {
        PlacementPhase placementPhase =
                EasyMock.partialMockBuilder(PlacementPhase.class).addMockedMethod("checkAndDecrementTurnCount")
                        .addMockedMethod("checkGameover").addMockedMethod("setPhaseComplete").createMock();
        this.addMock(placementPhase);

        EasyMock.expect(placementPhase.checkAndDecrementTurnCount(this.player)).andReturn(false);

        this.replayAll();

        placementPhase.turn(this.player);
    }

    @Test
    public void testTurnNoPlayableCards() {
        PlacementPhase placementPhase =
                EasyMock.partialMockBuilder(PlacementPhase.class).addMockedMethod("checkAndDecrementTurnCount")
                        .addMockedMethod("checkGameover").addMockedMethod("setPhaseComplete").createMock();
        placementPhase.utils = this.placementUtilities;
        placementPhase.gui = this.gui;
        this.addMock(placementPhase);

        EasyMock.expect(placementPhase.checkAndDecrementTurnCount(this.player)).andReturn(true);
        EasyMock.expect(this.placementUtilities.getPlayableCards(this.player.elementSpaceLogic, this.player.elements))
                .andReturn(Collections.emptyList());
        this.gui.alert(LocaleWrap.get("no_placements"), this.player.id, PromptType.WARNING);

        this.replayAll();

        placementPhase.turn(this.player);
    }

    @Test
    public void testTurnCardNotComplete() {
        PlacementPhase placementPhase =
                EasyMock.partialMockBuilder(PlacementPhase.class).addMockedMethod("checkAndDecrementTurnCount")
                        .addMockedMethod("checkGameover").addMockedMethod("setPhaseComplete").createMock();
        placementPhase.utils = this.placementUtilities;
        placementPhase.gui = this.gui;
        placementPhase.hatchingGround = this.hatchingGround;
        this.addMock(placementPhase);

        EasyMock.expect(placementPhase.checkAndDecrementTurnCount(this.player)).andReturn(true);
        EasyMock.expect(this.placementUtilities.getPlayableCards(this.player.elementSpaceLogic, this.player.elements))
                .andReturn(Arrays.asList(new Card()));
        placementPhase.setPhaseComplete(false);

        Card card = this.mock(Card.class);
        ElementSpace elementSpace = this.mock(ElementSpace.class);

        EasyMock.expect(this.placementUtilities.selectCard(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(card);
        EasyMock.expect(this.placementUtilities.selectElementSpace(card, this.player)).andReturn(elementSpace);
        this.placementUtilities.placeElements(elementSpace, this.player);
        EasyMock.expect(this.hatchingGround.logic.isComplete(card)).andReturn(false);

        this.replayAll();

        placementPhase.turn(this.player);
    }

    @Test
    public void testTurnCardCompleteDomestic() {
        PlacementPhase placementPhase =
                EasyMock.partialMockBuilder(PlacementPhase.class).addMockedMethod("checkAndDecrementTurnCount")
                        .addMockedMethod("checkGameover").addMockedMethod("setPhaseComplete").createMock();
        placementPhase.utils = this.placementUtilities;
        placementPhase.gui = this.gui;
        placementPhase.hatchingGround = this.hatchingGround;
        this.addMock(placementPhase);

        EasyMock.expect(placementPhase.checkAndDecrementTurnCount(this.player)).andReturn(true);
        EasyMock.expect(this.placementUtilities.getPlayableCards(this.player.elementSpaceLogic, this.player.elements))
                .andReturn(Arrays.asList(new Card()));
        placementPhase.setPhaseComplete(false);

        Card card = this.mock(Card.class);
        card.handler = new Handler(HandlerState.CARD);
        ElementSpace elementSpace = this.mock(ElementSpace.class);

        EasyMock.expect(this.placementUtilities.selectCard(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(card);
        EasyMock.expect(this.placementUtilities.selectElementSpace(card, this.player)).andReturn(elementSpace);
        this.placementUtilities.placeElements(elementSpace, this.player);
        EasyMock.expect(this.hatchingGround.logic.isComplete(card)).andReturn(true);

        this.replayAll();

        placementPhase.turn(this.player);
    }

    @Test
    public void testTurnCardCompleteWild() {
        PlacementPhase placementPhase =
                EasyMock.partialMockBuilder(PlacementPhase.class).addMockedMethod("checkAndDecrementTurnCount")
                        .addMockedMethod("checkGameover").addMockedMethod("setPhaseComplete").createMock();
        EggHatchingLogic wildEggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        placementPhase.utils = this.placementUtilities;
        placementPhase.gui = this.gui;
        placementPhase.hatchingGround = this.hatchingGround;
        placementPhase.wildEggHatchingLogic = wildEggHatchingLogic;
        this.addMock(wildEggHatchingLogic);
        this.addMock(placementPhase);

        EasyMock.expect(placementPhase.checkAndDecrementTurnCount(this.player)).andReturn(true);
        EasyMock.expect(this.placementUtilities.getPlayableCards(this.player.elementSpaceLogic, this.player.elements))
                .andReturn(Arrays.asList(new Card()));
        placementPhase.setPhaseComplete(false);

        Card card = this.mock(Card.class);
        ElementSpace elementSpace = this.mock(ElementSpace.class);

        EasyMock.expect(this.placementUtilities.selectCard(EasyMock.anyObject(), EasyMock.anyObject())).andReturn(card);
        EasyMock.expect(this.placementUtilities.selectElementSpace(card, this.player)).andReturn(elementSpace);
        this.placementUtilities.placeElements(elementSpace, this.player);
        EasyMock.expect(this.hatchingGround.logic.isComplete(card)).andReturn(true);

        wildEggHatchingLogic.hatchEgg(card, FakePlayer.getInstance());
        placementPhase.checkGameover();

        this.replayAll();

        placementPhase.turn(this.player);
    }


}

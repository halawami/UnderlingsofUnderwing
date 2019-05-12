package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.player.Player;

public class PlacementPhaseTests {

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
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getHandlerCount()).andReturn(2).anyTimes();
        List<Player> players = Arrays.asList(player);

        EasyMock.replay(player);
        PlacementPhase phase = new PlacementPhase(players, null, null, null, null, null);
        phase.setup();
        EasyMock.verify(player);

        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) getField(phase.getClass(), phase, "turnCounts");
        assertEquals(new Integer(2), map.get(player));
    }

    @Test
    public void testCheckTurnTwoHandlers() throws Exception {
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getHandlerCount()).andReturn(2).anyTimes();
        List<Player> players = Arrays.asList(player);

        EasyMock.replay(player);
        PlacementPhase phase = new PlacementPhase(players, null, null, null, null, null);
        phase.setup();
        Method turnCountMethod = getMethod(phase, "checkAndDecrementTurnCount", Player.class);
        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) getField(phase.getClass(), phase, "turnCounts");
        assertTrue((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(1), map.get(player));
        assertTrue((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(0), map.get(player));
        assertFalse((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(0), map.get(player));
        EasyMock.verify(player);
    }

    @Test
    public void testCheckTurnThreeHandlers() throws Exception {
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getHandlerCount()).andReturn(3).anyTimes();
        List<Player> players = Arrays.asList(player);

        EasyMock.replay(player);
        PlacementPhase phase = new PlacementPhase(players, null, null, null, null, null);
        phase.setup();
        Method turnCountMethod = getMethod(phase, "checkAndDecrementTurnCount", Player.class);
        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) getField(phase.getClass(), phase, "turnCounts");
        assertTrue((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(2), map.get(player));
        assertTrue((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(1), map.get(player));
        assertTrue((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(0), map.get(player));
        assertFalse((boolean) (turnCountMethod.invoke(phase, player)));
        assertEquals(new Integer(0), map.get(player));
        EasyMock.verify(player);
    }

    @Test
    public void testCheckGameover() throws Exception {
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getHandlerCount()).andReturn(3).anyTimes();
        List<Player> players = Arrays.asList(player);

        Card card = new Card();
        card.handler = new Handler(HandlerState.CARD);
        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card);
        HatchingGround hatchingGround = new HatchingGround(deck);

        EasyMock.replay(player, deck);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        PlacementPhase phase = new PlacementPhase(players, null, hatchingGround, null, null, null);
        phase.setup();
        Method gameoverMethod = getMethod(phase, "checkGameover");
        gameoverMethod.invoke(phase);
        EasyMock.verify(player, deck);

        assertFalse((boolean) getField(Phase.class, phase, "gameComplete"));
        assertFalse((boolean) getField(Phase.class, phase, "phaseComplete"));
    }

    @Test
    public void testCheckGameoverNoCards() throws Exception {
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getHandlerCount()).andReturn(3).anyTimes();
        List<Player> players = Arrays.asList(player);

        Card card = new Card();
        card.handler = new Handler(HandlerState.CARD);
        HatchingGround hatchingGround = new HatchingGround(null);

        EasyMock.replay(player);
        PlacementPhase phase = new PlacementPhase(players, null, hatchingGround, null, null, null);
        phase.setup();
        Method gameoverMethod = getMethod(phase, "checkGameover");
        gameoverMethod.invoke(phase);
        EasyMock.verify(player);

        assertTrue((boolean) getField(Phase.class, phase, "gameComplete"));
        assertTrue((boolean) getField(Phase.class, phase, "phaseComplete"));
    }

    @Test
    public void testMoreMovesNoChoices() {
        Display display = EasyMock.mock(Display.class);
        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Gui gui = new Gui(promptHandler, display);

        EasyMock.replay(display, promptHandler);

        boolean result = gui.getMoreMovesDecision(0, 0);

        EasyMock.verify(display, promptHandler);
        assertFalse(result);
    }

    @Test
    public void testMoreMovesTrue() {
        Display display = EasyMock.mock(Display.class);
        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Gui gui = new Gui(promptHandler, display);

        EasyMock.expect(promptHandler.promptDecision(EasyMock.anyString(), EasyMock.anyInt())).andReturn(true);

        EasyMock.replay(display, promptHandler);

        boolean result = gui.getMoreMovesDecision(1, 0);

        EasyMock.verify(display, promptHandler);
        assertTrue(result);
    }

}

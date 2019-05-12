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

import underlings.gui.Display;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.player.Player;

public class PlacementPhaseTests {

    public Map<Player, Integer> getMap(PlacementPhase phase) throws Exception, SecurityException {
        Field mapField = PlacementPhase.class.getDeclaredField("turnCounts");

        mapField.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Player, Integer> map = (Map<Player, Integer>) mapField.get(phase);
        return map;
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

        Map<Player, Integer> map = getMap(phase);
        assertEquals(new Integer(2), map.get(player));
    }

    @Test
    public void testCheckTurn() throws Exception {
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getHandlerCount()).andReturn(2).anyTimes();
        List<Player> players = Arrays.asList(player);

        EasyMock.replay(player);
        PlacementPhase phase = new PlacementPhase(players, null, null, null, null, null);
        phase.setup();
        Method turnCountMethod = getMethod(phase, "checkAndDecrementTurnCount", Player.class);
        assertTrue((boolean) (turnCountMethod.invoke(phase, player)));
        EasyMock.verify(player);

        Map<Player, Integer> map = getMap(phase);
        assertEquals(new Integer(1), map.get(player));
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

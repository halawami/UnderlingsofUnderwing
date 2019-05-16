package underlings.phase;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.TestUtils;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class DrawingPhaseTests {

    @Test
    public void testTurn() {
        Gui gui = EasyMock.mock(Gui.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        Player player = new Player(6, new HandlerFactory(), 0);

        List<Player> players = new ArrayList<>();
        players.add(player);

        Element element = new Element(ElementColor.BLUE);

        EasyMock.expect(gui.promptChoice(LocaleWrap.get("gui_element_giver"), player.getElementGivers(), 0))
                .andReturn(player.getElementGivers().get(0));
        EasyMock.expect(
                gui.promptChoice(LocaleWrap.get("gui_draw_choice"), player.getElementGivers().get(0).drawChoices, 0))
                .andReturn(DrawChoice.RANDOM);
        EasyMock.expect(elementBag.drawElement(DrawChoice.RANDOM)).andReturn(element);

        Phase drawingPhase = new DrawingPhase(players, gui, elementBag, null, null, null);

        EasyMock.replay(gui, elementBag);

        drawingPhase.setup();
        drawingPhase.turn(player);

        EasyMock.verify(gui, elementBag);
        assertTrue(player.getElements().contains(element));
    }

    @Test
    public void testSetup() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);

        for (Player player : players) {
            EasyMock.expect(player.getElementGivers()).andStubReturn(Collections.emptyList());
            player.onPhaseOne();
        }

        players.forEach(EasyMock::replay);

        DrawingPhase drawingPhase = new DrawingPhase(players, null, null, null, null, null);
        drawingPhase.setup();

        players.forEach(EasyMock::verify);
    }

    @Test
    public void testTearDown() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);

        for (Player player : players) {
            player.endPhaseOne();
        }

        players.forEach(EasyMock::replay);

        DrawingPhase drawingPhase = new DrawingPhase(players, null, null, null, null, null);
        drawingPhase.teardown();

        players.forEach(EasyMock::verify);
    }

}

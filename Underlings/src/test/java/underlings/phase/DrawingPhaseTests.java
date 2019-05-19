package underlings.phase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.NullElement;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class DrawingPhaseTests extends MockTest {

    @Test
    public void testTurn() {
        Gui gui = EasyMock.mock(Gui.class);

        Player player = new Player(6, new HandlerFactory(), 0);

        List<Player> players = new ArrayList<>();
        players.add(player);

        Element element = new Element(ElementColor.BLUE);

        EasyMock.expect(gui.promptChoice(LocaleUtilities.get("gui_element_giver"), player.getElementGivers(), 0))
                .andReturn(player.getElementGivers().get(0));
        EasyMock.expect(
                gui.promptChoice(LocaleUtilities.get("gui_draw_choice"), player.getElementGivers().get(0).drawChoices, 0))
                .andReturn(DrawChoice.RANDOM);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        EasyMock.expect(elementBag.drawElement(DrawChoice.RANDOM)).andReturn(element);

        DrawingPhase drawingPhase =
                EasyMock.partialMockBuilder(DrawingPhase.class).addMockedMethod("setPhaseComplete").createMock();
        drawingPhase.players = players;
        drawingPhase.gui = gui;
        drawingPhase.players = players;
        drawingPhase.elementBag = elementBag;

        drawingPhase.setPhaseComplete(false);

        EasyMock.replay(gui, elementBag, drawingPhase);

        drawingPhase.setup();
        drawingPhase.turn(player);

        EasyMock.verify(gui, elementBag, drawingPhase);
        assertTrue(player.getElements().contains(element));
    }

    @Test
    public void testTurnNullElement() {
        Gui gui = EasyMock.mock(Gui.class);

        Player player = new Player(6, new HandlerFactory(), 0);

        List<Player> players = new ArrayList<>();
        players.add(player);

        EasyMock.expect(gui.promptChoice(LocaleUtilities.get("gui_element_giver"), player.getElementGivers(), 0))
                .andReturn(player.getElementGivers().get(0));
        EasyMock.expect(
                gui.promptChoice(LocaleUtilities.get("gui_draw_choice"), player.getElementGivers().get(0).drawChoices, 0))
                .andReturn(DrawChoice.RANDOM);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        EasyMock.expect(elementBag.drawElement(DrawChoice.RANDOM)).andReturn(NullElement.getInstance());

        Phase drawingPhase = new DrawingPhase(players, gui, elementBag, null, null, null);

        EasyMock.replay(gui, elementBag);

        drawingPhase.setup();
        drawingPhase.turn(player);

        EasyMock.verify(gui, elementBag);
        assertFalse(player.getElements().contains(NullElement.getInstance()));
    }

    @Test
    public void testSetup() {
        List<Player> players = this.mockListOf(Player.class).withLengthOf(2);

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
        List<Player> players = this.mockListOf(Player.class).withLengthOf(2);

        for (Player player : players) {
            player.endPhaseOne();
        }

        players.forEach(EasyMock::replay);

        DrawingPhase drawingPhase = new DrawingPhase(players, null, null, null, null, null);
        drawingPhase.teardown();

        players.forEach(EasyMock::verify);
    }

}

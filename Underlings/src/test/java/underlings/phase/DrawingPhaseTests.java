package underlings.phase;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.phase.DrawingPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class DrawingPhaseTests {

    @Test
    public void testTurn() {

        Gui gui = EasyMock.mock(Gui.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        Player player = new Player(6, new HandlerFactory(), 0);

        List<Player> players = new ArrayList<Player>();
        players.add(player);

        Element element = new Element(ElementColor.BLUE);

        EasyMock.expect(gui.getDrawChoice(player.getElementGivers(), 0)).andReturn(DrawChoice.RANDOM);
        EasyMock.expect(elementBag.drawElement(DrawChoice.RANDOM)).andReturn(element);

        Phase drawingPhase = new DrawingPhase(players, gui, elementBag, null, null, null);

        EasyMock.replay(gui, elementBag);

        drawingPhase.setup();
        drawingPhase.turn(player);

        EasyMock.verify(gui, elementBag);
        assertTrue(player.getElements().contains(element));

    }

}

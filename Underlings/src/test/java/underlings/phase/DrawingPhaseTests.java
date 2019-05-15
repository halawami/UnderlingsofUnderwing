package underlings.phase;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.player.Player;

public class DrawingPhaseTests {

    @Test
    public void testTurn() {

        Gui gui = EasyMock.mock(Gui.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        Player player = EasyMock.niceMock(Player.class);

        List<Player> players = new ArrayList<Player>();
        players.add(player);
        List<ElementGiver> elementGivers = Arrays.asList(new ElementGiver(DrawChoice.RANDOM));

        Element element = new Element(ElementColor.BLUE);

        player.onPhaseOne();
        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(gui.getDrawChoice(elementGivers, 0)).andReturn(DrawChoice.RANDOM);
        EasyMock.expect(elementBag.drawElement(DrawChoice.RANDOM)).andReturn(element);
        player.endPhaseOne();

        Phase drawingPhase = new DrawingPhase(players, gui, elementBag, null, null, null);

        EasyMock.replay(player, gui, elementBag);

        drawingPhase.setup();
        drawingPhase.turn(player);

        EasyMock.verify(player, gui, elementBag);
        assertTrue(player.getElements().contains(element));

    }

}

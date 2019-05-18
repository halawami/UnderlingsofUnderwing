package underlings.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import underlings.TestUtils;
import underlings.element.Element;

public class StealElementsTests {

    @Test
    public void testStealNoElements() {
        Player playerToStealFrom = new Player(0, null, 0);
        Player stealer = new Player(0, null, 0);

        stealer.stealAllElementsFromPlayer(playerToStealFrom);

        assertEquals(0, playerToStealFrom.elements.size());
        assertEquals(0, stealer.elements.size());
    }

    @Test
    public void testStealOneElement() {
        Player playerToStealFrom = new Player(0, null, 0);
        List<Element> elementsToBeStolen = TestUtils.mockListOf(Element.class).withLength(1);
        playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);
        Player stealer = new Player(0, null, 0);

        stealer.stealAllElementsFromPlayer(playerToStealFrom);

        assertEquals(0, playerToStealFrom.elements.size());
        assertEquals(1, stealer.elements.size());
        assertTrue(stealer.elements.containsAll(elementsToBeStolen));
    }

    @Test
    public void testStealTwoElements() {
        Player playerToStealFrom = new Player(0, null, 0);
        List<Element> elementsToBeStolen = TestUtils.mockListOf(Element.class).withLength(2);
        playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);
        Player stealer = new Player(0, null, 0);

        stealer.stealAllElementsFromPlayer(playerToStealFrom);

        assertEquals(0, playerToStealFrom.elements.size());
        assertEquals(2, stealer.elements.size());
        assertTrue(stealer.elements.containsAll(elementsToBeStolen));
    }

    @Test
    public void testStealTwoElementsWhileHavingTwoElements() {
        Player playerToStealFrom = new Player(0, null, 0);
        List<Element> elementsToBeStolen = TestUtils.mockListOf(Element.class).withLength(2);
        playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);
        Player stealer = new Player(0, null, 0);
        List<Element> stealerElementsBefore = TestUtils.mockListOf(Element.class).withLength(2);
        stealer.elements = new ArrayList<>(stealerElementsBefore);

        stealer.stealAllElementsFromPlayer(playerToStealFrom);

        assertEquals(0, playerToStealFrom.elements.size());
        assertEquals(4, stealer.elements.size());
        assertTrue(stealer.elements.containsAll(stealerElementsBefore));
        assertTrue(stealer.elements.containsAll(elementsToBeStolen));
    }


}

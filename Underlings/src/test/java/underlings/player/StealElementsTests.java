package underlings.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.Constructors;
import underlings.MockTest;

import underlings.TestUtils;
import underlings.element.Element;

public class StealElementsTests extends MockTest {

    private Player playerToStealFrom, stealer;

    @Before
    public void init() {
        this.playerToStealFrom = TestUtils.Player();
        this.stealer = TestUtils.Player();
    }

    @Test
    public void testStealNoElements() {
        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(0, this.stealer.elements.size());
    }

    @Test
    public void testStealOneElement() {
        List<Element> elementsToBeStolen = this.mockListOf(Element.class).withLength(1);
        this.playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);

        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(1, this.stealer.elements.size());
        assertTrue(this.stealer.elements.containsAll(elementsToBeStolen));
    }

    @Test
    public void testStealTwoElements() {
        List<Element> elementsToBeStolen = this.mockListOf(Element.class).withLength(2);
        this.playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);

        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(2, this.stealer.elements.size());
        assertTrue(this.stealer.elements.containsAll(elementsToBeStolen));
    }

    @Test
    public void testStealTwoElementsWhileHavingTwoElements() {
        List<Element> elementsToBeStolen = this.mockListOf(Element.class).withLength(2);
        this.playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);
        List<Element> stealerElementsBefore = this.mockListOf(Element.class).withLength(2);
        this.stealer.elements = new ArrayList<>(stealerElementsBefore);

        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(4, this.stealer.elements.size());
        assertTrue(this.stealer.elements.containsAll(stealerElementsBefore));
        assertTrue(this.stealer.elements.containsAll(elementsToBeStolen));
    }


}

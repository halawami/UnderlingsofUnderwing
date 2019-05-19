package underlings.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.TestUtils;
import underlings.element.Element;

public class StealElementsTests extends MockTest {

    private Player playerToStealFrom;
    private Player stealer;

    @Before
    public void init() {
        this.playerToStealFrom = TestUtils.makePlayer();
        this.stealer = TestUtils.makePlayer();
    }

    @Test
    public void testStealNoElements() {
        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(0, this.stealer.elements.size());
    }

    @Test
    public void testStealOneElement() {
        List<Element> elementsToBeStolen = this.mockListOf(Element.class).withLengthOf(1);
        this.playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);

        this.replayAll();
        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(1, this.stealer.elements.size());
        assertTrue(this.stealer.elements.containsAll(elementsToBeStolen));
    }

    @Test
    public void testStealTwoElements() {
        List<Element> elementsToBeStolen = this.mockListOf(Element.class).withLengthOf(2);
        this.playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);

        this.replayAll();
        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(2, this.stealer.elements.size());
        assertTrue(this.stealer.elements.containsAll(elementsToBeStolen));
    }

    @Test
    public void testStealTwoElementsWhileHavingTwoElements() {
        List<Element> elementsToBeStolen = this.mockListOf(Element.class).withLengthOf(2);
        this.playerToStealFrom.elements = new ArrayList<>(elementsToBeStolen);
        List<Element> stealerElementsBefore = this.mockListOf(Element.class).withLengthOf(2);
        this.stealer.elements = new ArrayList<>(stealerElementsBefore);

        this.replayAll();
        this.stealer.stealAllElementsFromPlayer(this.playerToStealFrom);

        assertEquals(0, this.playerToStealFrom.elements.size());
        assertEquals(4, this.stealer.elements.size());
        assertTrue(this.stealer.elements.containsAll(stealerElementsBefore));
        assertTrue(this.stealer.elements.containsAll(elementsToBeStolen));
    }


}

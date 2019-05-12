package underlings.player;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.effect.EffectTestUtils;
import underlings.element.Element;

public class StealAllElementsTests {

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
        List<Element> elementsToBeStolen = EffectTestUtils.getMockObjects(Element.class, 1);
        playerToStealFrom.elements = elementsToBeStolen;
        Player stealer = new Player(0, null, 0);

        stealer.stealAllElementsFromPlayer(playerToStealFrom);

        assertEquals(0, playerToStealFrom.elements.size());
        assertEquals(1, stealer.elements.size());
        assertEquals(elementsToBeStolen.get(0), stealer.elements.get(0));
    }



}

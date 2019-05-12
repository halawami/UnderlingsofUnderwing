package underlings.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StealAllElementsTests {

    @Test
    public void testStealNoElements() {
        Player playerToStealFrom = new Player(0, null, 0);
        Player stealer = new Player(0, null, 0);

        stealer.stealAllElementsFromPlayer(playerToStealFrom);

        assertEquals(0, playerToStealFrom.elements.size());
        assertEquals(0, stealer.elements.size());
    }

}

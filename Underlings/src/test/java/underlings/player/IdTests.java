package underlings.player;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import underlings.handler.HandlerFactory;

public class IdTests {

    @Test
    public void testOne() {
        Player p = new Player(2, new HandlerFactory(), 1);
        assertEquals(1, p.getId());
    }

    @Test
    public void testSix() {
        Player p = new Player(2, new HandlerFactory(), 6);
        assertEquals(6, p.getId());
    }

    @Test
    public void testToString() {
        Player p = new Player(2, new HandlerFactory(), 1);

        assertEquals("Player 1", p.toString());
    }

}

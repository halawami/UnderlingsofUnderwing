package underlings.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.handler.HandlerFactory;

public class PlayerIdTests {

    @Test
    public void testIdZero() {
        Player p = new Player(2, new HandlerFactory(), 0);
        assertEquals(0, p.getId());
    }

    @Test
    public void testIdOne() {
        Player p = new Player(2, new HandlerFactory(), 1);
        assertEquals(1, p.getId());
    }

    @Test
    public void testIdTwo() {
        Player p = new Player(2, new HandlerFactory(), 2);
        assertEquals(2, p.getId());
    }

    @Test
    public void testToString() {
        Player p = new Player(2, new HandlerFactory(), 0);

        assertEquals("Player 0", p.toString());
    }

}

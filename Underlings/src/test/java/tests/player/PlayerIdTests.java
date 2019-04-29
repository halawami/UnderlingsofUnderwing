package tests.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class PlayerIdTests {

    @Test
    public void IdZero() {
        Player p = new Player(2, new HandlerFactory(), 0);
        assertEquals(0, p.getPlayerId());
    }

    @Test
    public void IdOne() {
        Player p = new Player(2, new HandlerFactory(), 1);
        assertEquals(1, p.getPlayerId());
    }

    @Test
    public void IdTwo() {
        Player p = new Player(2, new HandlerFactory(), 2);
        assertEquals(2, p.getPlayerId());
    }

    @Test
    public void testToString() {
        Player p = new Player(2, new HandlerFactory(), 0);

        assertEquals("Player 0", p.toString());
    }

}

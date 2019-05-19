package underlings.player;

import static org.junit.Assert.assertEquals;

import java.text.MessageFormat;
import java.util.Arrays;

import org.junit.Test;

import underlings.handler.HandlerFactory;
import underlings.utilities.LocaleUtilities;

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

        assertEquals(MessageFormat.format(LocaleUtilities.get("player_number"), p.getId()), p.toString());
    }

    @Test
    public void testTwoPlayers() {
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory(), Arrays.asList());
        Player p = playerFactory.createPlayer(2);
        assertEquals(1, p.getId());
        Player p2 = playerFactory.createPlayer(2);
        assertEquals(2, p2.getId());
    }

}
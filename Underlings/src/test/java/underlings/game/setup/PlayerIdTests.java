package underlings.game.setup;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class PlayerIdTests {

    @Test
    public void testOnePlayer() throws IOException {
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory(), Arrays.asList());
        Player p = playerFactory.createPlayer(2);
        assertEquals(1, p.getId());
    }

    @Test
    public void testTwoPlayers() throws IOException {
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory(), Arrays.asList());
        Player p = playerFactory.createPlayer(2);
        assertEquals(1, p.getId());
        Player p2 = playerFactory.createPlayer(2);
        assertEquals(2, p2.getId());
    }

}

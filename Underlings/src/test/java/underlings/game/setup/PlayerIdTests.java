package underlings.game.setup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class PlayerIdTests {

    @Test
    public void testOnePlayer() {
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player p = playerFactory.createPlayer(2);
        assertEquals(1, p.getPlayerId());
    }

    @Test
    public void testTwoPlayers() {
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player p = playerFactory.createPlayer(2);
        assertEquals(1, p.getPlayerId());
        Player p2 = playerFactory.createPlayer(2);
        assertEquals(2, p2.getPlayerId());
    }

}

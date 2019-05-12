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
        assertEquals(0, p.getPlayerId());
    }

    @Test
    public void testIdTwo() {
        Player p = new Player(2, new HandlerFactory(), 2);
        assertEquals(2, p.getPlayerId());
    }

}

package underlings.player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class RemoveHandlerTests {

    private Player player;

    @Before
    public void init() {
        this.player = new Player(6, new HandlerFactory(), 0);
    }

    @Test
    public void test3Handlers() {
        this.gainHandler(1);

        this.player.loseHandler();
        assertEquals(2, this.player.getHandlerCount());
    }

    @Test
    public void testMaxHandlers() {
        this.gainHandler(4);

        this.player.loseHandler();
        assertEquals(5, this.player.getHandlerCount());
    }

    @Test
    public void test2Handlers() {
        this.player.loseHandler();
        assertEquals(2, this.player.getHandlerCount());
    }

    private void gainHandler(int amt) {
        for (int i = 0; i < amt; i++) {
            this.player.gainHandler();
        }
    }

}

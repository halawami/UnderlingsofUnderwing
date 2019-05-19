package underlings.player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.TestUtils;

public class RemoveHandlerTests {

    private Player player;

    @Before
    public void init() {
        this.player = TestUtils.makePlayer();
    }

    @Test
    public void testThree() {
        this.gainHandler(1);

        this.player.loseHandler();
        assertEquals(2, this.player.getHandlerCount());
    }

    @Test
    public void testMax() {
        this.gainHandler(4);

        this.player.loseHandler();
        assertEquals(5, this.player.getHandlerCount());
    }

    @Test
    public void testTwo() {
        this.player.loseHandler();
        assertEquals(2, this.player.getHandlerCount());
    }

    private void gainHandler(int amt) {
        for (int i = 0; i < amt; i++) {
            this.player.gainHandler();
        }
    }

}

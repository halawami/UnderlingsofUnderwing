package underlings.player;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import underlings.TestUtils;

public class AddHandlerTests {

    private Player player;

    @Before
    public void init() {
        this.player = TestUtils.Player();
    }

    @Test
    public void testTwo() {
        this.gainHandler(1);
        assertEquals(3, this.player.getHandlerCount());
    }

    @Test
    public void testAlmostMax() {
        this.gainHandler(3);

        this.player.gainHandler();
        assertEquals(6, this.player.getHandlerCount());
    }

    @Test
    public void testMax() {
        this.gainHandler(5);

        this.player.gainHandler();
        assertEquals(6, this.player.getHandlerCount());
    }

    private void gainHandler(int amt) {
        for (int i = 0; i < amt; i++) {
            this.player.gainHandler();
        }
    }
}

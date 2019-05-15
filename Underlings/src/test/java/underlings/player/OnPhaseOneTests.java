package underlings.player;

import org.junit.Test;

public class OnPhaseOneTests {

    @Test
    public void testNoObservers() {
        //TODO: find a better way of testing this
        Player player = new Player(0, null, 0);
        player.onPhaseOne();
    }

}

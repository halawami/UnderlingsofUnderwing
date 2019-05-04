package underlings.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.handler.HandlerFactory;

public class EffectTests {

    @Test
    public void testNoUnhatchedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        assertEquals(0, player.getAllEffects().size());
    }



}

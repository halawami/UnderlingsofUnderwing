package underlings.card.effect.domestic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class TwoHandlersOnFieldSpaceEffectTests {

    @Test
    public void testEffect() {
        Player player = new Player(6, new HandlerFactory(), 2);

        Effect effect = new TwoHandlersOnFieldSpaceEffect();
        effect.on(player).apply();

        assertEquals(2, player.maxHandlersOnSpace);
    }
}

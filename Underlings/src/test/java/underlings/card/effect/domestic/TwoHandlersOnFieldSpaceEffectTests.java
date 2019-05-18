package underlings.card.effect.domestic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import underlings.Constructors;
import underlings.card.effect.Effect;
import underlings.player.Player;

public class TwoHandlersOnFieldSpaceEffectTests {

    @Test
    public void testEffect() {
        Player player = Constructors.Player();

        Effect effect = new TwoHandlersOnFieldSpaceEffect();
        effect.on(player).apply();

        assertEquals(2, player.maxHandlersOnSpace);
    }
}

package underlings.card.effect.domestic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.players.player.TwoHandlersOnFieldSpaceEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class TwoHandlersOnFieldSpaceEffectTests {

    @Test
    public void testEffect() {
        Player player = TestUtils.makePlayer();

        Effect effect = new TwoHandlersOnFieldSpaceEffect();
        effect.on(player).apply();

        assertEquals(2, player.maxHandlersOnSpace);
    }

    @Test
    public void testToString() {
        Effect effect = new TwoHandlersOnFieldSpaceEffect();
        assertEquals(LocaleWrap.get("two_handler_effect"), effect.toString());
    }
}

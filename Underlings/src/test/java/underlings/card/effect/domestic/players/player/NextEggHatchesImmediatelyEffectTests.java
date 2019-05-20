package underlings.card.effect.domestic.players.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class NextEggHatchesImmediatelyEffectTests {

    @Test
    public void testApply() {
        Player player = new Player(2, new HandlerFactory(), 0);

        NextEggHatchesImmediatelyEffect effect = new NextEggHatchesImmediatelyEffect();
        effect.on(player).apply();

        assertEquals(0, player.hatchingTime);
    }

    @Test
    public void testToString() {
        Effect effect = new NextEggHatchesImmediatelyEffect();
        assertEquals(LocaleUtilities.get("egg_hatches_early_effect"), effect.toString());
    }

}

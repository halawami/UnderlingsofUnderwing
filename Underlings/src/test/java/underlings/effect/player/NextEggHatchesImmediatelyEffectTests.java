package underlings.effect.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.NextEggHatchesImmediatelyEffect;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class NextEggHatchesImmediatelyEffectTests {

    @Test
    public void testApply() {
        Player player = new Player(2, new HandlerFactory(), 0);
        Card card = new Card();

        NextEggHatchesImmediatelyEffect effect = new NextEggHatchesImmediatelyEffect();
        effect.on(player).apply();

        assertEquals(0, player.hatchingTime);
    }

}

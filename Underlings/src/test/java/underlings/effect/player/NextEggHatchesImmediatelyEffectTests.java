package underlings.effect.player;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.NextEggHatchesImmediatelyEffect;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class NextEggHatchesImmediatelyEffectTests {

    @Test
    public void testApply() {
        Player player = new Player(2, new HandlerFactory(), 0);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Card card = new Card();
        eggHatchingLogic.hatchEgg(card, false, player);

        NextEggHatchesImmediatelyEffect effect = new NextEggHatchesImmediatelyEffect();
        effect.on(player).apply();

        assertEquals(0, player.hatchingTime);
    }

}

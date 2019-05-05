package underlings.effect.player;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.CollectElementEachPhaseOneEffect;
import underlings.player.Player;

public class CollectElementEachPhaseOneEffectTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Effect testedEffect = new CollectElementEachPhaseOneEffect();
        testedEffect.on(player);

        EasyMock.replay(player);

        testedEffect.apply();

        EasyMock.verify(player);
    }

}

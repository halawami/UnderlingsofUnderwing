package underlings.effect.player;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.wild.LoseHandlerEffect;
import underlings.player.Player;

public class LoseHandlerEffectTests {

    @Test
    public void testEffect() {
        Player player = EasyMock.mock(Player.class);
        LoseHandlerEffect testedEffect = new LoseHandlerEffect();

        player.loseHandler();
        EasyMock.replay(player);

        testedEffect.on(player).apply();

        EasyMock.verify(player);
    }

}

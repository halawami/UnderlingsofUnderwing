package underlings.effect.player;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.domestic.DrawElementsOfChoiceEffect;
import underlings.player.Player;

public class DrawElementsOfChoiceEffectTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        DrawElementsOfChoiceEffect testedEffect = new DrawElementsOfChoiceEffect();

        player.addObserverEffect(testedEffect);

        EasyMock.replay(player);

        testedEffect.on(player).apply();

        EasyMock.verify(player);
    }

}

package effect.player;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import card.effect.Effect;
import card.effect.domestic.GainOneHandlerEffect;
import player.Player;

public class GainOneHandlerTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Effect gainOneHandler = new GainOneHandlerEffect();
        gainOneHandler.on(player);
        player.gainHandler();
        EasyMock.replay(player);

        gainOneHandler.apply();

        EasyMock.verify(player);
    }

    @Test
    public void testToString() {
        Effect gainOneHandler = new GainOneHandlerEffect();

        assertEquals("Gain one handler", gainOneHandler.toString());
    }


}

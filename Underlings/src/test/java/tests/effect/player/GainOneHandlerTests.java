package tests.effect.player;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.GainOneHandlerEffect;
import underlings.player.Player;

import static org.junit.Assert.assertEquals;

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

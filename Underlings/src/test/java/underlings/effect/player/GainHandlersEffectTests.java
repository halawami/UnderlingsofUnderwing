package underlings.effect.player;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.GainHandlersEffect;
import underlings.player.Player;

public class GainHandlersEffectTests {

    @Test
    public void testOneHandler() {
        Player player = EasyMock.mock(Player.class);
        GainHandlersEffect gainOneHandler = new GainHandlersEffect();
        gainOneHandler.numberOfHandlers = 1;
        gainOneHandler.on(player);
        player.gainHandler();
        EasyMock.replay(player);

        gainOneHandler.apply();

        EasyMock.verify(player);
    }

    @Test
    public void testFourHandlers() {
        Player player = EasyMock.mock(Player.class);
        GainHandlersEffect gainOneHandler = new GainHandlersEffect();
        gainOneHandler.numberOfHandlers = 4;
        gainOneHandler.on(player);
        for (int i = 0; i < 4; i++) {
            player.gainHandler();
        }
        EasyMock.replay(player);

        gainOneHandler.apply();

        EasyMock.verify(player);
    }

    @Test
    public void testToString() {
        Effect gainOneHandler = new GainHandlersEffect();

        assertEquals("Gain one handler", gainOneHandler.toString());
    }

}

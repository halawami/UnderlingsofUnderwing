package underlings.effect.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.domestic.GainHandlersEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class GainHandlersEffectTests extends MockTest {

    @Test
    public void testOneHandler() {
        Player player = this.mock(Player.class);
        GainHandlersEffect gainOneHandler = new GainHandlersEffect();
        gainOneHandler.numberOfHandlers = 1;
        gainOneHandler.on(player);
        player.gainHandler();

        this.replayAll();

        gainOneHandler.apply();
    }

    @Test
    public void testFourHandlers() {
        Player player = this.mock(Player.class);
        GainHandlersEffect gainOneHandler = new GainHandlersEffect();
        gainOneHandler.numberOfHandlers = 4;
        gainOneHandler.on(player);
        for (int i = 0; i < 4; i++) {
            player.gainHandler();
        }

        this.replayAll();

        gainOneHandler.apply();
    }

    @Test
    public void testToString() {
        GainHandlersEffect effect = new GainHandlersEffect();
        assertEquals(LocaleWrap.format("gain_handler_effect", effect.numberOfHandlers), effect.toString());
    }

}

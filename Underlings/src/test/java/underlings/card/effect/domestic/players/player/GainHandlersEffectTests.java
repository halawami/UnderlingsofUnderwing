package underlings.card.effect.domestic.players.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.domestic.players.player.GainHandlersEffect;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class GainHandlersEffectTests extends MockTest {

    @Test
    public void testOneHandler() {
        Player player = this.mock(Player.class);
        GainHandlersEffect effect = new GainHandlersEffect();
        effect.numberOfHandlers = 1;
        effect.on(player);
        player.gainHandler();

        this.replayAll();

        effect.apply();
    }

    @Test
    public void testFourHandlers() {
        Player player = this.mock(Player.class);
        GainHandlersEffect effect = new GainHandlersEffect();
        effect.numberOfHandlers = 4;
        effect.on(player);
        for (int i = 0; i < 4; i++) {
            player.gainHandler();
        }

        this.replayAll();

        effect.apply();
    }

    @Test
    public void testToString() {
        GainHandlersEffect effect = new GainHandlersEffect();
        assertEquals(LocaleUtilities.format("gain_handler_effect", effect.numberOfHandlers), effect.toString());
    }

}

package underlings.effect.player;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.players.LoseHandlerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class LoseHandlerEffectTests extends MockTest {

    @Test
    public void testLoseHandlerTwoPlayers() {
        List<Player> mockPlayers = this.mockListOf(Player.class).withLengthOf(2);
        LoseHandlerEffect effect = new LoseHandlerEffect();

        mockPlayers.forEach(Player::loseHandler);

        this.replayAll();

        effect.on(mockPlayers).apply();
    }

    @Test
    public void testLoseHandlerSixPlayers() {
        List<Player> mockPlayers = this.mockListOf(Player.class).withLengthOf(6);
        LoseHandlerEffect effect = new LoseHandlerEffect();

        mockPlayers.forEach(Player::loseHandler);

        this.replayAll();

        effect.on(mockPlayers).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new LoseHandlerEffect();
        assertEquals(LocaleUtilities.get("lose_handler_effect"), effect.toString());
    }

}

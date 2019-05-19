package underlings.effect.player;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.LoseHandlerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class LoseHandlerEffectTests extends MockTest {

    @Test
    public void testLoseHandlerTwoPlayers() {
        List<Player> mockPlayers = this.mockListOf(Player.class).withLengthOf(2);
        LoseHandlerEffect testedEffect = new LoseHandlerEffect();

        mockPlayers.forEach(Player::loseHandler);

        this.replayAll();

        testedEffect.on(mockPlayers).apply();
    }

    @Test
    public void testLoseHandlerSixPlayers() {
        List<Player> mockPlayers = this.mockListOf(Player.class).withLengthOf(6);
        LoseHandlerEffect testedEffect = new LoseHandlerEffect();

        mockPlayers.forEach(Player::loseHandler);

        this.replayAll();

        testedEffect.on(mockPlayers).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new LoseHandlerEffect();
        assertEquals(LocaleWrap.get("lose_handler_effect"), effect.toString());
    }

}

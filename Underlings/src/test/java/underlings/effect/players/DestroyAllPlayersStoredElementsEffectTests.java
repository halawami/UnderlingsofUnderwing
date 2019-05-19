package underlings.effect.players;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.players.DestroyAllPlayersStoredElementsEffect;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class DestroyAllPlayersStoredElementsEffectTests extends MockTest {

    @Test
    public void testApplyOnTwoPlayer() {
        this.testApplyOnPlayers(2);
    }

    @Test
    public void testApplyOnSixPlayer() {
        this.testApplyOnPlayers(6);
    }

    private void testApplyOnPlayers(int numberOfPlayers) {
        List<Player> players = this.mockListOf(Player.class).withLengthOf(numberOfPlayers);
        Effect effect = new DestroyAllPlayersStoredElementsEffect();
        effect.on(players);

        players.forEach(Player::destroyAllElements);

        this.replayAll();

        effect.apply();
    }

    @Test
    public void testToString() {
        Effect effect = new DestroyAllPlayersStoredElementsEffect();
        assertEquals(LocaleUtilities.get("destroy_all_players_stored_elemetns"), effect.toString());
    }

}

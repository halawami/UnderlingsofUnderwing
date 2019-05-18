package underlings.effect.players;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.DestroyAllPlayersStoredElements;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class DestroyAllPlayersStoredElementsTests {

    @Test
    public void testApplyOnTwoPlayer() {
        this.testApplyOnPlayers(2);
    }

    @Test
    public void testApplyOnSixPlayer() {
        this.testApplyOnPlayers(6);
    }

    public void testApplyOnPlayers(int numberOfPlayers) {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(numberOfPlayers);
        Effect testedEffect = new DestroyAllPlayersStoredElements();
        testedEffect.on(players);

        players.forEach(Player::destroyAllElements);

        players.forEach(EasyMock::replay);

        testedEffect.apply();

        players.forEach(EasyMock::verify);
    }

    @Test
    public void testToString() {
        Effect effect = new DestroyAllPlayersStoredElements();
        assertEquals(LocaleWrap.get("destroy_all_players_stored_elemetns"), effect.toString());
    }

}

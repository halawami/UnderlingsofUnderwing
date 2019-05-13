package underlings.effect.players;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.DestroyAllPlayersStoredElements;
import underlings.player.Player;

public class DestroyAllPlayersStoredElementsTests {

    @Test
    public void testApplyOnTwoPlayer() {
        this.testApplyOnPlayers(6);
    }

    @Test
    public void testApplyOnSixPlayer() {
        this.testApplyOnPlayers(6);
    }

    public void testApplyOnPlayers(int numberOfPlayers) {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(6);
        Effect testedEffect = new DestroyAllPlayersStoredElements();
        testedEffect.on(players);

        players.forEach(Player::destroyAllElements);

        players.forEach(EasyMock::replay);

        testedEffect.apply();

        players.forEach(EasyMock::verify);
    }

}

package underlings.effect.players;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.wild.DestroyAllPlayersStoredElements;
import underlings.effect.EffectTestUtils;
import underlings.player.Player;

public class DestroyAllPlayersStoredElementsTests {

    @Test
    public void testApplyOnTwoPlayer() {
        List<Player> players = EffectTestUtils.getMockObjects(Player.class, 2);
        Effect testedEffect = new DestroyAllPlayersStoredElements();
        testedEffect.on(players);

        players.forEach(Player::destroyAllElements);

        players.forEach(EasyMock::replay);

        testedEffect.apply();

        players.forEach(EasyMock::verify);
    }

}

package underlings.effect.players;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.wild.DrawOneLessElementEffect;
import underlings.player.Player;

public class DrawOneLessElementEffectTests {

    @Test
    public void testApplyTwoPlayers() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);
        DrawOneLessElementEffect testedEffect = new DrawOneLessElementEffect();

        for (Player player : players) {
            player.addObserverEffect(testedEffect);
        }

        players.forEach(EasyMock::replay);

        testedEffect.on(players).apply();

        players.forEach(EasyMock::verify);
    }

    @Test
    public void testApplySixPlayers() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);
        DrawOneLessElementEffect testedEffect = new DrawOneLessElementEffect();

        for (Player player : players) {
            player.addObserverEffect(testedEffect);
        }

        players.forEach(EasyMock::replay);

        testedEffect.on(players).apply();

        players.forEach(EasyMock::verify);
    }
}

package underlings.effect.player;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.wild.LoseHandlerEffect;
import underlings.player.Player;

public class LoseHandlerEffectTests {

    @Test
    public void testLoseHandlerTwoPlayers() {
        List<Player> mockPlayers = this.getMockedObjects(Player.class, 2);
        LoseHandlerEffect testedEffect = new LoseHandlerEffect();

        mockPlayers.forEach(Player::loseHandler);
        mockPlayers.forEach(EasyMock::replay);

        testedEffect.on(mockPlayers).apply();

        mockPlayers.forEach(EasyMock::verify);
    }

    @Test
    public void testLoseHandlerSixPlayers() {
        List<Player> mockPlayers = this.getMockedObjects(Player.class, 6);
        LoseHandlerEffect testedEffect = new LoseHandlerEffect();

        mockPlayers.forEach(Player::loseHandler);
        mockPlayers.forEach(EasyMock::replay);

        testedEffect.on(mockPlayers).apply();

        mockPlayers.forEach(EasyMock::verify);
    }

    private <T> List<T> getMockedObjects(Class<T> objectsClass, int numberOfObjects) {
        List<T> mockedObjects = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            mockedObjects.add(EasyMock.mock(objectsClass));
        }
        return mockedObjects;
    }


}

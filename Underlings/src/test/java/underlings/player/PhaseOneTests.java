package underlings.player;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.ObserverEffect;

public class PhaseOneTests extends MockTest {

    @Test
    public void testNoObservers() {
        this.testWithObservers(0);
    }

    @Test
    public void testOneObserver() {
        this.testWithObservers(1);
    }

    @Test
    public void testTwoObserver() {
        this.testWithObservers(2);
    }

    private void testWithObservers(int numberOfObservers) {
        List<ObserverEffect> observerEffects = this.mockListOf(ObserverEffect.class).withLength(numberOfObservers);
        Player player = new Player(0, null, 0);
        observerEffects.forEach(player::addObserverEffect);

        observerEffects.forEach(observerEffect -> observerEffect.onPhaseOne(player));

        observerEffects.forEach(EasyMock::replay);

        player.onPhaseOne();

        observerEffects.forEach(EasyMock::verify);
    }

    @Test
    public void testEndPhaseOne() {
        Player player = EasyMock.partialMockBuilder(Player.class)
                .addMockedMethod("useEffectElementGivers").createMock();

        player.useEffectElementGivers(false);

        EasyMock.replay(player);

        player.endPhaseOne();

        EasyMock.verify(player);
    }

}

package underlings.player;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.ObserverEffect;

public class OnPhaseOneTests {

    @Test
    public void testNoObservers() {
        //TODO: find a better way of testing this
        Player player = new Player(0, null, 0);
        player.onPhaseOne();
    }

    @Test
    public void testOneObserver() {
        ObserverEffect observerEffect = EasyMock.mock(ObserverEffect.class);
        Player player = new Player(0, null, 0);
        player.addObserverEffect(observerEffect);

        observerEffect.onPhaseOne(player);

        EasyMock.replay(observerEffect);

        player.onPhaseOne();

        EasyMock.verify(observerEffect);
    }

}

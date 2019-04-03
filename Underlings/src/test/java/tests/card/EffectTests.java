package tests.card;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.CompositeEffect;
import underlings.card.Effect;

public class EffectTests {

    @Test
    public void testCompositeEffect(){
        Effect mockedEffect = EasyMock.mock(Effect.class);
        mockedEffect.apply();
        EasyMock.replay(mockedEffect);

        Effect testCompositeEffect = new CompositeEffect(mockedEffect);
        testCompositeEffect.apply();

        EasyMock.verify(mockedEffect);
    }
}

package underlings.effect.handler;

import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.RemoveAllHandlersFromAllEggsInPlay;

public class RemoveAllHandlersFromAllEggsInPlayEffectTests {

    @Test
    public void testNoHandler() {
        //TODO: figure out a better test for this case
        Card cardWithNoHandler = new Card();
        RemoveAllHandlersFromAllEggsInPlay testedEffect = new RemoveAllHandlersFromAllEggsInPlay();

        testedEffect.applyOnCardInPlay(cardWithNoHandler, null, null);
    }

}

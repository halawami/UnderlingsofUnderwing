package underlings.effect.handler;

import org.junit.Assert;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.RemoveAllHandlersFromAllEggsInPlay;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class RemoveAllHandlersFromAllEggsInPlayEffectTests {

    @Test
    public void testNoHandler() {
        //TODO: figure out a better test for this case
        Card cardWithNoHandler = new Card();
        RemoveAllHandlersFromAllEggsInPlay testedEffect = new RemoveAllHandlersFromAllEggsInPlay();

        testedEffect.applyOnCardInPlay(cardWithNoHandler, null, null);
    }

    @Test
    public void testPlayerHandler() {
        Card card = new Card();
        Handler handler = new Handler(HandlerState.CARD);
        card.handler = handler;
        RemoveAllHandlersFromAllEggsInPlay testedEffect = new RemoveAllHandlersFromAllEggsInPlay();

        testedEffect.applyOnCardInPlay(card, null, null);

        Assert.assertNull(card.handler);
        Assert.assertEquals(HandlerState.BREAK_ROOM, handler.getState());
    }

}

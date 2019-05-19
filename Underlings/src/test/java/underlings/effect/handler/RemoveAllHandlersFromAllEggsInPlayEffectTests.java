package underlings.effect.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.RemoveAllHandlersFromAllEggsInPlay;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleWrap;

public class RemoveAllHandlersFromAllEggsInPlayEffectTests extends MockTest {

    @Test
    public void testNoHandler() {
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        Card cardWithNoHandler = new Card();
        RemoveAllHandlersFromAllEggsInPlay testedEffect = new RemoveAllHandlersFromAllEggsInPlay();

        handlerMovementLogic.move(null, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(cardWithNoHandler);

        this.replayAll();

        testedEffect.applyOnCardInPlay(cardWithNoHandler, null, null, handlerMovementLogic);
    }

    @Test
    public void testPlayerHandler() {
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        Card card = new Card();
        Handler handler = new Handler(HandlerState.CARD);
        card.handler = handler;
        RemoveAllHandlersFromAllEggsInPlay testedEffect = new RemoveAllHandlersFromAllEggsInPlay();

        testedEffect.applyOnCardInPlay(card, null, null, handlerMovementLogic);

        Assert.assertNull(card.handler);
        Assert.assertEquals(HandlerState.BREAK_ROOM, handler.getState());
    }

    @Test
    public void testWildHandler() {
        Card card = new Card();
        Handler handler = WildHandler.getInstance();
        card.handler = handler;
        RemoveAllHandlersFromAllEggsInPlay testedEffect = new RemoveAllHandlersFromAllEggsInPlay();

        testedEffect.applyOnCardInPlay(card, null, null, this.handlerMovementLogic);

        Assert.assertEquals(WildHandler.getInstance(), card.handler);
        Assert.assertEquals(HandlerState.CARD, handler.getState());
    }

    @Test
    public void testToString() {
        Effect effect = new RemoveAllHandlersFromAllEggsInPlay();
        assertEquals(LocaleWrap.get("remove_all_handlers_from_eggs_effect"), effect.toString());
    }
}

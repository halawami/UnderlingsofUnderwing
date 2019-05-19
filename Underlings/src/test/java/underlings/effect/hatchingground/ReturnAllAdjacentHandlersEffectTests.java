package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentHandlersEffect;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleWrap;

public class ReturnAllAdjacentHandlersEffectTests extends MockTest {

    @Test
    public void testNoHandler() {
        this.testWithHandler(null);
    }

    @Test
    public void testWildHandler() {
        this.testWithHandler(WildHandler.getInstance());
    }

    @Test
    public void testNormalHandler() {
        this.testWithHandler(new Handler(HandlerState.CARD));
    }

    private void testWithHandler(Handler handler) {
        Card adjacentCard = this.mock(Card.class);
        adjacentCard.handler = handler;
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.READY_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentCard);

        this.replayAll();

        ReturnAllAdjacentHandlersEffect testedEffect = new ReturnAllAdjacentHandlersEffect();
        testedEffect.applyOnAdjacentEgg(adjacentCard, handlerMovementLogic);
    }

    @Test
    public void testToString() {
        Effect effect = new ReturnAllAdjacentHandlersEffect();
        assertEquals(LocaleWrap.get("return_adjacent_handlers"), effect.toString());
    }

}

package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.ReturnAllAdjacentHandlersEffect;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;

public class ReturnAllAdjacentHandlersEffectTests {

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

    public void testWithHandler(Handler handler) {
        Card adjacentCard = EasyMock.mock(Card.class);
        adjacentCard.handler = handler;
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.READY_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentCard);

        EasyMock.replay(adjacentCard, handlerMovementLogic);

        ReturnAllAdjacentHandlersEffect testedEffect = new ReturnAllAdjacentHandlersEffect();
        testedEffect.applyOnAdjacentEgg(adjacentCard, null, null, null, null, handlerMovementLogic, null);

        EasyMock.verify(adjacentCard, handlerMovementLogic);
    }

}

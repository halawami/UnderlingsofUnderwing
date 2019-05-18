package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.ReturnAllAdjacentHandlersEffect;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;

public class ReturnAllAdjacentHandlersEffectTests {

    @Test
    public void testNoHandler() {
        Card adjacentCard = EasyMock.mock(Card.class);
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.READY_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentCard);

        EasyMock.replay(adjacentCard, handlerMovementLogic);

        ReturnAllAdjacentHandlersEffect testedEffect = new ReturnAllAdjacentHandlersEffect();
        testedEffect.applyOnAdjacentEgg(adjacentCard, null, null, null, null, handlerMovementLogic, null);

        EasyMock.verify(adjacentCard, handlerMovementLogic);
    }

    @Test
    public void testWildHandler() {
        Card adjacentCard = EasyMock.mock(Card.class);
        adjacentCard.handler = WildHandler.getInstance();
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.READY_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentCard);

        EasyMock.replay(adjacentCard, handlerMovementLogic);

        ReturnAllAdjacentHandlersEffect testedEffect = new ReturnAllAdjacentHandlersEffect();
        testedEffect.applyOnAdjacentEgg(adjacentCard, null, null, null, null, handlerMovementLogic, null);

        EasyMock.verify(adjacentCard, handlerMovementLogic);
    }

}

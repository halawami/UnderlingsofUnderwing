package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.ReturnAllAdjacentCardsToDeckEffect;
import underlings.game.Deck;
import underlings.handler.HandlerMovementLogic;

public class ReturnAllAdjacentCardsToDeckEffectTests {

    @Test
    public void testApplyNoHandler() {
        Card adjacentCard = EasyMock.mock(Card.class);
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Deck deck = EasyMock.mock(Deck.class);

        deck.addCard(adjacentCard);

        EasyMock.replay(adjacentCard, handlerMovementLogic, deck);

        Effect testedEffect = new ReturnAllAdjacentCardsToDeckEffect();
        testedEffect.on(adjacentCard).on(handlerMovementLogic).on(deck).apply();

        EasyMock.verify(adjacentCard, handlerMovementLogic, deck);
    }

}

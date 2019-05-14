package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.ReturnAllAdjacentCardsToDeckEffect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;

public class ReturnAllAdjacentCardsToDeckEffectTests {

    @Test
    public void testApplyNoHandler() {
        Card adjacentCard = EasyMock.mock(Card.class);
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Deck deck = EasyMock.mock(Deck.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.BREAK_ROOM, 0);
        hatchingGround.replaceCard(adjacentCard);
        deck.addCard(adjacentCard);

        EasyMock.replay(adjacentCard, handlerMovementLogic, deck, hatchingGround);

        ReturnAllAdjacentCardsToDeckEffect testedEffect = new ReturnAllAdjacentCardsToDeckEffect();
        testedEffect.applyOnAdjacentEgg(adjacentCard, null, null, null, deck, handlerMovementLogic, hatchingGround);

        EasyMock.verify(adjacentCard, handlerMovementLogic, deck, hatchingGround);
    }

    @Test
    public void testApplyWildHandler() {

    }

}

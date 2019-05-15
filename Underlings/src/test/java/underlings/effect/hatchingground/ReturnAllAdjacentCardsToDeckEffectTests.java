package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.ReturnAllAdjacentCardsToDeckEffect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;

public class ReturnAllAdjacentCardsToDeckEffectTests {

    @Test
    public void testApplyNoHandler() {
        this.testApplyWithHandler(null);
    }

    @Test
    public void testApplyWildHandler() {
        this.testApplyWithHandler(WildHandler.getInstance());
    }

    @Test
    public void testApplyNormalHandler() {
        this.testApplyWithHandler(new Handler(HandlerState.CARD));
    }

    private void testApplyWithHandler(Handler handler) {
        Card adjacentCard = EasyMock.mock(Card.class);
        adjacentCard.handler = handler;
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Deck deck = EasyMock.mock(Deck.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentCard);
        hatchingGround.replaceCard(adjacentCard);
        deck.addCard(adjacentCard);

        EasyMock.replay(adjacentCard, handlerMovementLogic, deck, hatchingGround);

        ReturnAllAdjacentCardsToDeckEffect testedEffect = new ReturnAllAdjacentCardsToDeckEffect();
        testedEffect.applyOnAdjacentEgg(adjacentCard, null, null, null, deck, handlerMovementLogic, hatchingGround);

        EasyMock.verify(adjacentCard, handlerMovementLogic, deck, hatchingGround);
    }


}

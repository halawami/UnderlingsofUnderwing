package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.adjacenteggs.ReturnAllAdjacentCardsToDeckEffect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleWrap;

public class ReturnAllAdjacentCardsToDeckEffectTests extends MockTest {

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
        Card adjacentCard = this.mock(Card.class);
        adjacentCard.handler = handler;
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);

        handlerMovementLogic.move(adjacentCard.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentCard);
        hatchingGround.replaceCard(adjacentCard);

        Deck deck = this.mock(Deck.class);
        deck.addCard(adjacentCard, true);

        this.replayAll();

        ReturnAllAdjacentCardsToDeckEffect effect = new ReturnAllAdjacentCardsToDeckEffect();
        effect.applyOnAdjacentEgg(adjacentCard, deck, handlerMovementLogic, hatchingGround);
    }

    @Test
    public void testToString() {
        Effect effect = new ReturnAllAdjacentCardsToDeckEffect();
        assertEquals(LocaleWrap.get("return_adjacent_eggs_effect"), effect.toString());
    }
}

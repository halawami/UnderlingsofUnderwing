package underlings.card.effect.wild.alleggsinplay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleUtilities;

public class RemoveAllHandlersFromAllEggsInPlayEffectTests extends MockTest {

    @Test
    public void testNoHandler() {
        this.testWithHandler(null);
    }

    @Test
    public void testPlayerHandler() {
        this.testWithHandler(new Handler(HandlerState.CARD));
    }

    @Test
    public void testWildHandler() {
        this.testWithHandler(WildHandler.getInstance());
    }

    private void testWithHandler(Handler handler) {
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        Card card = new Card();
        card.handler = handler;

        handlerMovementLogic.move(card.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(card);

        this.replayAll();

        RemoveAllHandlersFromAllEggsInPlayEffect effect = new RemoveAllHandlersFromAllEggsInPlayEffect();
        effect.applyOnCardInPlay(card, handlerMovementLogic);
    }

    @Test
    public void testToString() {
        Effect effect = new RemoveAllHandlersFromAllEggsInPlayEffect();
        assertEquals(LocaleUtilities.get("remove_all_handlers_from_eggs_effect"), effect.toString());
    }
}

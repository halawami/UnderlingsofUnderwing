package underlings.player;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class EffectTests {

    @Test
    public void testNoUnhatchedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        assertEquals(0, player.getAllEffects().size());
    }

    @Test
    public void testOneHatchedEgg() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = EasyMock.mock(Card.class);
        Handler handler = EasyMock.mock(Handler.class);
        handler.moveToState(HandlerState.CARD);
        card.handler = handler;
        player.unhatchedCards.add(card);


        EasyMock.replay(card, handler);


        EasyMock.verify(card, handler);
        assertEquals(HandlerState.READY_ROOM, card.handler.getState());
        assertEquals(1, player.getAllEffects().size());
        assertEquals(1, player.hatchedCards.size());
        assertEquals(card, player.hatchedCards.get(0));
        assertEquals(0, player.unhatchedCards.size());
    }

}

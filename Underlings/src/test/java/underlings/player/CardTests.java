package underlings.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class CardTests {

    @Test
    public void testOneCompletedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.handler = player.getHandlers().get(0);
        card.handler.moveToState(HandlerState.INCUBATION);

        assertTrue(player.hasCard(card));
        assertEquals(1, player.unhatchedCards.size());
        assertEquals(card, player.unhatchedCards.get(0));
    }

    @Test
    public void testTwoCompletedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = EasyMock.mock(Card.class);
        card.handler = player.getHandlers().get(0);
        Card card2 = EasyMock.mock(Card.class);
        card2.handler = player.getHandlers().get(1);
        card.handler.moveToState(HandlerState.INCUBATION);
        card2.handler.moveToState(HandlerState.INCUBATION);

        EasyMock.replay(card, card2);

        EasyMock.verify(card, card2);
        assertTrue(player.hasCard(card));
        assertTrue(player.hasCard(card2));
        assertEquals(2, player.unhatchedCards.size());
        assertEquals(card, player.unhatchedCards.get(0));
        assertEquals(card2, player.unhatchedCards.get(1));
    }

    @Test
    public void testTwoCompletedEggsDifferentPlayer() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = EasyMock.mock(Card.class);
        card.handler = player.getHandlers().get(0);
        Card card2 = EasyMock.mock(Card.class);
        Handler handler = EasyMock.mock(Handler.class);
        card2.handler = handler;
        card.handler.moveToState(HandlerState.INCUBATION);

        EasyMock.replay(card, card2, handler);

        EasyMock.verify(card, card2, handler);
        assertTrue(player.hasCard(card));
        assertFalse(player.hasCard(card2));
        assertEquals(1, player.unhatchedCards.size());
        assertEquals(card, player.unhatchedCards.get(0));
    }
}

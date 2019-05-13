package underlings.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class CardTests {

    @Test
    public void testNoCompletedEggs() {
        Card card = new Card();
        Handler handler = new HandlerFactory().createHandler();
        card.handler = handler;
        Player player = new Player(6, new HandlerFactory(), 0);
        assertFalse(player.hasCard(card));
    }

    @Test
    public void testOneCompletedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.handler = player.getHandlers().get(0);

        assertTrue(player.hasCard(card));
        assertEquals(card.handler.getState(), HandlerState.INCUBATION);
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

        EasyMock.replay(card, card2);

        EasyMock.verify(card, card2);
        assertTrue(player.hasCard(card));
        assertTrue(player.hasCard(card2));
        assertEquals(card.handler.getState(), HandlerState.INCUBATION);
        assertEquals(card2.handler.getState(), HandlerState.INCUBATION);
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

        EasyMock.replay(card, card2, handler);

        EasyMock.verify(card, card2, handler);
        assertTrue(player.hasCard(card));
        assertFalse(player.hasCard(card2));
        assertEquals(card.handler.getState(), HandlerState.INCUBATION);
        assertEquals(1, player.unhatchedCards.size());
        assertEquals(card, player.unhatchedCards.get(0));
    }

    @Test
    public void testMostValuableOneDragon() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.points = 1;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        assertEquals(Arrays.asList(card), player.getMostValuableDragons());
    }

    @Test
    public void testMostValuableMultipleDragons() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.points = 1;
        Card card2 = new Card();
        card2.points = 2;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card2);
        List<Card> result = new LinkedList<>();
        result.add(card2);
        assertEquals(result, player.getMostValuableDragons());
        assertEquals(1, player.getMostValuableDragons().size());
    }
}

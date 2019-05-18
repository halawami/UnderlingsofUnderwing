package underlings.player;

import static org.junit.Assert.assertEquals;
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
        player.moveToIncubation(card, player.hatchingTime);
        assertEquals(HandlerState.INCUBATION, card.handler.getState());
    }

    @Test
    public void testOneCompletedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.handler = player.getHandlers().get(0);

        player.moveToIncubation(card, player.hatchingTime);
        assertEquals(card.handler.getState(), HandlerState.INCUBATION);
        assertEquals(1, player.unhatchedCards.size());
        assertTrue(player.unhatchedCards.containsKey(card));
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
        player.moveToIncubation(card, player.hatchingTime);
        player.moveToIncubation(card2, player.hatchingTime);
        assertEquals(HandlerState.INCUBATION, card.handler.getState());
        assertEquals(HandlerState.INCUBATION, card2.handler.getState());
        assertEquals(2, player.unhatchedCards.size());
        assertTrue(player.unhatchedCards.containsKey(card));
        assertTrue(player.unhatchedCards.containsKey(card2));
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
        player.moveToIncubation(card, player.hatchingTime);
        assertEquals(HandlerState.INCUBATION, card.handler.getState());
        assertEquals(1, player.unhatchedCards.size());
        assertTrue(player.unhatchedCards.containsKey(card));
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

    @Test
    public void testMostValuableMultipleDragonsOneHigher() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.points = 2;
        Card card2 = new Card();
        card2.points = 1;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card2);
        List<Card> result = new LinkedList<>();
        result.add(card);
        assertEquals(result, player.getMostValuableDragons());
        assertEquals(1, player.getMostValuableDragons().size());
    }

    @Test
    public void testMostValuableMultipleDragonsTie() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = new Card();
        card.points = 1;
        Card card2 = new Card();
        card2.points = 2;
        Card card3 = new Card();
        card3.points = 2;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card2);
        player.hatchedCards.add(card3);
        List<Card> result = new LinkedList<>();
        result.add(card2);
        result.add(card3);
        assertEquals(result, player.getMostValuableDragons());
        assertEquals(2, player.getMostValuableDragons().size());
    }

    @Test
    public void testNoHatchedCards() {
        Player player = new Player(6, new HandlerFactory(), 0);
        player.hatchedCards = new LinkedList<>();
        assertEquals(Arrays.asList(), player.getMostValuableDragons());
        assertEquals(0, player.getMostValuableDragons().size());
    }
}

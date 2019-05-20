package underlings.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class HatchedCardTests {

    private Player player;
    private Card card;
    private Card cardTwo;

    @Before
    public void init() {
        this.player = TestUtils.makePlayer();
        this.card = new Card();
        this.card.handler = this.player.handlers.get(0);
        this.cardTwo = new Card();
        this.cardTwo.handler = this.player.handlers.get(1);

    }

    @Test
    public void testNoCompletedEggs() {
        this.player.moveToIncubation(this.card, this.player.hatchingTime);
        assertEquals(HandlerState.INCUBATION, this.card.handler.getState());
    }

    @Test
    public void testOneCompletedEgg() {
        this.player.moveToIncubation(this.card, this.player.hatchingTime);
        assertEquals(this.card.handler.getState(), HandlerState.INCUBATION);
        assertEquals(1, this.player.unhatchedCards.size());
        assertTrue(this.player.unhatchedCards.containsKey(this.card));
    }

    @Test

    public void testTwoCompletedEggs() {
        this.player.moveToIncubation(this.card, this.player.hatchingTime);
        this.player.moveToIncubation(this.cardTwo, this.player.hatchingTime);

        assertEquals(HandlerState.INCUBATION, this.card.handler.state);
        assertEquals(HandlerState.INCUBATION, this.cardTwo.handler.state);
        assertEquals(2, this.player.unhatchedCards.size());
        assertTrue(this.player.unhatchedCards.containsKey(this.card));
        assertTrue(this.player.unhatchedCards.containsKey(this.cardTwo));
    }

    @Test
    public void testMostValuableOneDragon() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = TestUtils.makeCard(1);
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        assertEquals(Arrays.asList(card), player.getMostValuableDragons());
    }

    @Test
    public void testMostValuableMultipleDragonsOneHigher() {
        Card card = TestUtils.makeCard(2);
        Card card2 = TestUtils.makeCard(1);
        this.player.hatchedCards.add(card);
        this.player.hatchedCards.add(card2);
        List<Card> result = new ArrayList<>();
        result.add(card);
        assertEquals(result, this.player.getMostValuableDragons());
        assertEquals(1, this.player.getMostValuableDragons().size());
    }

    @Test
    public void testMostValuableMultipleDragonsTie() {
        Card card = TestUtils.makeCard(1);
        Card card2 = TestUtils.makeCard(2);
        Card card3 = TestUtils.makeCard(2);

        this.player.hatchedCards.add(card);
        this.player.hatchedCards.add(card2);
        this.player.hatchedCards.add(card3);
        List<Card> result = new ArrayList<>();
        result.add(card2);
        result.add(card3);
        assertEquals(result, this.player.getMostValuableDragons());
        assertEquals(2, this.player.getMostValuableDragons().size());
    }

    @Test
    public void testNoHatchedCards() {
        this.player.hatchedCards = new LinkedList<>();
        assertEquals(Arrays.asList(), this.player.getMostValuableDragons());
        assertEquals(0, this.player.getMostValuableDragons().size());
    }
}

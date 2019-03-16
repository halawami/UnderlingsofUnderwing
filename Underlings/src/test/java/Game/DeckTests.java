package Game;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DeckTests {

    private Deck deck;
    final private int DECK_SIZE = 50;

    @Before
    public void init() {
        Stack<Card> cards = new Stack<Card>();
        for (int i = 0; i < DECK_SIZE; i++) {
            cards.push(new Card());
        }
        this.deck = new Deck(cards);
    }

    @Test
    public void testDeckSetUp() {
        assertEquals(DECK_SIZE, deck.getSize());
    }


    @Test
    public void testDraw() {
        Card first = new Card();
        Stack<Card> cards = new Stack<>();
        cards.push(first);
        this.deck = new Deck(cards);
        assertEquals(first, this.deck.draw());
    }

    @Test
    public void testDrawEmpty() {
        Stack<Card> cards = new Stack<>();
        this.deck = new Deck(cards);
        assertThrows(IllegalStateException.class, () -> this.deck.draw());
    }


}

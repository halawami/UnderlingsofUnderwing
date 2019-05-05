package underlings.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;

public class DeckTests {

    private Deck deck;
    private static final int DECK_SIZE = 50;

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
        assertEquals(DECK_SIZE, this.deck.getSize());
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

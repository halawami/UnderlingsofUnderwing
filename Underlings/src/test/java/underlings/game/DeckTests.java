package underlings.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Stack;
import java.util.function.Consumer;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.EmptyCard;

public class DeckTests {

    private Deck deck;
    private static final int DECK_SIZE = 50;
    Consumer consumer;

    @Before
    public void init() {
        Stack<Card> cards = new Stack<Card>();
        for (int i = 0; i < DECK_SIZE; i++) {
            cards.push(new Card());
        }
        this.consumer = EasyMock.mock(Consumer.class);
        this.deck = new Deck(cards, this.consumer);
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
        this.deck = new Deck(cards, this.consumer);
        assertEquals(first, this.deck.draw());
    }

    @Test
    public void testDrawEmpty() {
        Stack<Card> cards = new Stack<>();
        this.deck = new Deck(cards, this.consumer);
        assertEquals(EmptyCard.getInstance(), this.deck.draw());
    }

    @Test
    public void testAddCardNoShuffle() {
        Card card = new Card();
        this.deck.addCard(card, false);
        assertEquals(51, this.deck.getSize());
    }

    @Test
    public void testAddEmptyCard() {
        Card card = EmptyCard.getInstance();
        this.deck.addCard(card, false);
        assertEquals(50, this.deck.getSize());
        for (int i = 0; i < 50; i++) {
            assertNotEquals(EmptyCard.getInstance(), this.deck.draw());
        }
    }

    @Test
    public void testAddCardEmptyDeck() {
        Stack<Card> cards = new Stack<>();
        this.deck = new Deck(cards, this.consumer);
        Card card = new Card();
        this.deck.addCard(card, false);
        assertEquals(1, this.deck.getSize());
    }

}

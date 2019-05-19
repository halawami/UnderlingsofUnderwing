package underlings.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.hatchingground.Deck;

public class DeckTests {

    private Deck deck;
    private static final int DECK_SIZE = 50;
    @SuppressWarnings("rawtypes")
    Consumer<List> consumer;

    @SuppressWarnings("unchecked")
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
        assertTrue(this.deck.cards.contains(card));
    }

    @Test
    public void testAddCardShuffle() {
        Card card = new Card();
        this.consumer.accept(this.deck.cards);
        EasyMock.replay(this.consumer);

        this.deck.addCard(card, true);

        assertTrue(this.deck.cards.contains(card));
        EasyMock.verify(this.consumer);
    }

    @Test
    public void testAddEmptyCard() {
        Card card = EmptyCard.getInstance();
        this.deck.addCard(card, false);

        assertFalse(this.deck.cards.contains(card));
    }



}

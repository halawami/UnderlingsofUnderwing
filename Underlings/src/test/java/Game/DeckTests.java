package Game;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class DeckTests {

    private Deck deck;
    final private int DECK_SIZE =50;

    @Before
    public void init(){
        List<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < DECK_SIZE; i++) {
            cards.add(new Card());
        }
        this.deck = new Deck(cards);
    }

    @Test
    public void testDeckSetUp() {
        assertEquals(DECK_SIZE, deck.getSize());
    }
}

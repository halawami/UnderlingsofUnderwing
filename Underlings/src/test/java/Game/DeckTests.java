package Game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTests {

    @Test
    public void testDeckSetUp(){
        Deck deck = new Deck();
        assertEquals(50, deck.getSize());
    }
}

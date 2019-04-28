package tests.card;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import underlings.card.Card;

public class CardTests {

    @Test
    public void testToString() {
        Card card = new Card();
        card.name = "test card";

        assertEquals("test card", card.toString());
    }

}

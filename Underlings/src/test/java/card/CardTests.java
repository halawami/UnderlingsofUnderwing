package card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardTests {

    @Test
    public void testToString() {
        Card card = new Card();
        card.name = "test card";

        assertEquals("test card", card.toString());
    }

}

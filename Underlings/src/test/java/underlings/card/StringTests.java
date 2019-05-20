package underlings.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringTests {

    @Test
    public void testToString() {
        Card card = new Card();
        card.name = "test";

        assertEquals("test", card.toString());
    }

}

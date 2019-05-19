package underlings.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.utilities.LocaleWrap;

public class CardTests {

    @Test
    public void testToString() {
        Card card = new Card();
        card.name = LocaleWrap.get("test_card");

        assertEquals(LocaleWrap.get("test_card"), card.toString());
    }

}

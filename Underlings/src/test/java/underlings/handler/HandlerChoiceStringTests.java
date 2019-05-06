package underlings.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HandlerChoiceStringTests {

    @Test
    public void testStayString() {
        HandlerChoice choice = HandlerChoice.STAY;

        assertEquals("STAY", choice.toString());
    }

    @Test
    public void testFieldString() {
        HandlerChoice choice = HandlerChoice.FIELD;

        assertEquals("Move to FIELD", choice.toString());
    }

    @Test
    public void testCardString() {
        HandlerChoice choice = HandlerChoice.CARD;

        assertEquals("Move to CARD", choice.toString());
    }
}

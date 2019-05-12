package underlings.handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerState;

public class ChoiceTests {

    @Test
    public void testToString() {
        assertEquals("Stay", this.testToString(HandlerChoice.STAY));
        assertEquals("Move to White Field Space", this.testToString(HandlerChoice.FIELD_WHITESPACE));
        assertEquals("Move to Field", this.testToString(HandlerChoice.FIELD));
        assertEquals("Move to Card", this.testToString(HandlerChoice.CARD));
        assertEquals("Move to Ready Room", this.testToString(HandlerChoice.READY_ROOM));
        assertEquals("Move to Break Room", this.testToString(HandlerChoice.BREAK_ROOM));
    }

    private String testToString(HandlerChoice handlerChoice) {
        return handlerChoice.toString();
    }

    @Test
    public void tesPossibilities() {
        this.comparePossibilities(HandlerState.READY_ROOM, HandlerChoice.STAY, HandlerChoice.FIELD_WHITESPACE,
                HandlerChoice.FIELD, HandlerChoice.CARD);
        this.comparePossibilities(HandlerState.BREAK_ROOM, HandlerChoice.READY_ROOM);
        this.comparePossibilities(HandlerState.FIELD_WHITESPACE, HandlerChoice.BREAK_ROOM);
        this.comparePossibilities(HandlerState.FIELD, HandlerChoice.STAY, HandlerChoice.BREAK_ROOM);
        this.comparePossibilities(HandlerState.INCUBATION, HandlerChoice.STAY);
        this.comparePossibilities(HandlerState.CARD, HandlerChoice.STAY, HandlerChoice.BREAK_ROOM);
    }

    private void comparePossibilities(HandlerState state, HandlerChoice... possibilities) {
        Handler handler = new Handler(state);
        List<HandlerChoice> possibleStates = handler.getPossibleChoices();
        assertEquals(possibilities.length, possibleStates.size());
        for (HandlerChoice possibility : possibilities) {
            assertTrue(possibleStates.contains(possibility));
        }
    }

}

package handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import handler.Handler;
import handler.HandlerChoice;
import handler.HandlerState;

public class ChoiceTests {

    @Test
    public void testReadyRoom() {
        this.testPossibilities(HandlerState.READY_ROOM, HandlerChoice.STAY, HandlerChoice.FIELD_WHITESPACE,
                HandlerChoice.FIELD, HandlerChoice.CARD);
    }

    @Test
    public void testBreakRoom() {
        this.testPossibilities(HandlerState.BREAK_ROOM, HandlerChoice.READY_ROOM);
    }

    @Test
    public void testFieldWhitespace() {
        this.testPossibilities(HandlerState.FIELD_WHITESPACE, HandlerChoice.BREAK_ROOM);
    }

    @Test
    public void testField() {
        this.testPossibilities(HandlerState.FIELD, HandlerChoice.STAY, HandlerChoice.BREAK_ROOM);
    }

    @Test
    public void testIncubation() {
        this.testPossibilities(HandlerState.INCUBATION, HandlerChoice.STAY);
    }

    @Test
    public void testCard() {
        this.testPossibilities(HandlerState.CARD, HandlerChoice.STAY, HandlerChoice.BREAK_ROOM);
    }

    private void testPossibilities(HandlerState state, HandlerChoice... possibilities) {
        Handler handler = new Handler(state);
        List<HandlerChoice> possibleStates = handler.getPossibleChoices();
        assertEquals(possibilities.length, possibleStates.size());
        for (HandlerChoice possibility : possibilities) {
            assertTrue(possibleStates.contains(possibility));
        }
    }

}

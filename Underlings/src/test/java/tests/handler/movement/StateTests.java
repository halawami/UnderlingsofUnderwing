package tests.handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class StateTests {

    @Test
    public void testReadyRoomPossibleStates() {
        this.testPossibilities(HandlerState.READY_ROOM, HandlerState.CARD,
                HandlerState.READY_ROOM, HandlerState.FIELD,
                HandlerState.FIELD_WHITESPACE);
    }

    @Test
    public void testReadyRoomToUnclaimedEgg() {
        this.testMove(HandlerState.READY_ROOM, HandlerState.CARD);
    }

    @Test
    public void testReadyRoomToReadyRoom() {
        this.testMove(HandlerState.READY_ROOM, HandlerState.READY_ROOM);
    }

    @Test
    public void testReadyRoomToFieldWhite() {
        this.testMove(HandlerState.READY_ROOM, HandlerState.FIELD_WHITESPACE);
    }

    @Test
    public void testReadyRoomToField() {
        this.testMove(HandlerState.READY_ROOM, HandlerState.FIELD);
    }

    @Test
    public void testFieldPossibleStates() {
        this.testPossibilities(HandlerState.FIELD, HandlerState.FIELD,
                HandlerState.BREAK_ROOM);
    }

    @Test
    public void testFieldMoveClockwise() {
        this.testMove(HandlerState.FIELD, HandlerState.FIELD);
    }

    @Test
    public void testFieldToBreakRoom() {
        this.testMove(HandlerState.FIELD, HandlerState.BREAK_ROOM);
    }

    @Test
    public void testCardPossibleStates() {
        this.testPossibilities(HandlerState.CARD, HandlerState.CARD,
                HandlerState.BREAK_ROOM);
    }

    @Test
    public void testCardToBreakRoom() {
        this.testMove(HandlerState.CARD, HandlerState.BREAK_ROOM);
    }

    @Test
    public void testCardToCard() {
        this.testMove(HandlerState.CARD, HandlerState.CARD);
    }

    @Test
    public void testBreakRoomPossibleStates() {
        this.testPossibilities(HandlerState.BREAK_ROOM,
                HandlerState.READY_ROOM);
    }

    @Test
    public void testBreakRoomToReadyRoom() {
        this.testMove(HandlerState.BREAK_ROOM, HandlerState.BREAK_ROOM);
    }

    @Test
    public void testFieldWhiteSpacePossibleStates() {
        this.testPossibilities(HandlerState.FIELD_WHITESPACE,
                HandlerState.BREAK_ROOM);
    }

    @Test
    public void testFieldWhiteSpaceToBreakRoom() {
        this.testMove(HandlerState.FIELD_WHITESPACE, HandlerState.BREAK_ROOM);
    }

    @Test
    public void testIncubationPossibleStates() {
        this.testPossibilities(HandlerState.INCUBATION, HandlerState.INCUBATION,
                HandlerState.READY_ROOM);
    }

    @Test
    public void testIncubationToIncubation() {
        this.testMove(HandlerState.INCUBATION, HandlerState.INCUBATION);
    }

    @Test
    public void testIncubationToReadyRoom() {
        this.testMove(HandlerState.INCUBATION, HandlerState.READY_ROOM);
    }

    private void testPossibilities(HandlerState state,
            HandlerState... possibilities) {
        Handler handler = new Handler(state);
        List<HandlerState> possibleStates = handler.getPossibleStates();
        assertEquals(possibilities.length, possibleStates.size());
        for (HandlerState possibility : possibilities) {
            assertTrue(possibleStates.contains(possibility));
        }
    }

    private void testMove(HandlerState from, HandlerState to) {
        Handler handler = new Handler(from);
        handler.moveToState(to);
        assertEquals(to, handler.getState());
    }

}

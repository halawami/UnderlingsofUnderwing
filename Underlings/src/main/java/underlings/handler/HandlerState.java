package underlings.handler;

import java.util.Arrays;
import java.util.List;

public enum HandlerState {
    READY_ROOM, CARD, FIELD, FIELD_WHITESPACE, BREAK_ROOM, INCUBATION;

    static {
        READY_ROOM.canMoveTo(READY_ROOM, CARD, FIELD, FIELD_WHITESPACE);
        CARD.canMoveTo(CARD, BREAK_ROOM);
        FIELD.canMoveTo(FIELD, BREAK_ROOM);
        FIELD_WHITESPACE.canMoveTo(BREAK_ROOM);
        BREAK_ROOM.canMoveTo(READY_ROOM);
        INCUBATION.canMoveTo(INCUBATION, READY_ROOM);
    }

    public List<HandlerState> allowedStates;

    public void canMoveTo(HandlerState... allowedStates) {
        this.allowedStates = Arrays.asList(allowedStates);
    }

}

package underlings.handler;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import underlings.utilities.LocaleWrap;

public enum HandlerChoice {

    STAY, FIELD_WHITESPACE, FIELD, CARD, READY_ROOM, BREAK_ROOM;

    private HandlerState state;
    private static HashMap<HandlerState, List<HandlerChoice>> movementChoices = new HashMap<>();

    static {
        canMoveTo(HandlerState.READY_ROOM, STAY, FIELD_WHITESPACE, FIELD, CARD);
        canMoveTo(HandlerState.BREAK_ROOM, READY_ROOM);
        canMoveTo(HandlerState.FIELD_WHITESPACE, BREAK_ROOM);
        canMoveTo(HandlerState.FIELD, STAY, BREAK_ROOM);
        canMoveTo(HandlerState.INCUBATION, STAY);
        canMoveTo(HandlerState.CARD, STAY, BREAK_ROOM);

        FIELD_WHITESPACE.state = HandlerState.FIELD_WHITESPACE;
        FIELD.state = HandlerState.FIELD;
        CARD.state = HandlerState.CARD;
        READY_ROOM.state = HandlerState.READY_ROOM;
        BREAK_ROOM.state = HandlerState.BREAK_ROOM;
    }

    private static void canMoveTo(HandlerState handlerState, HandlerChoice... choices) {
        movementChoices.put(handlerState, Arrays.asList(choices));
    }

    public static List<HandlerChoice> getMovements(HandlerState handlerState) {
        return movementChoices.get(handlerState);
    }

    @Override
    public String toString() {
        if (this == HandlerChoice.STAY) {
            return LocaleWrap.get("handler_choice_stay");
        }
        return MessageFormat.format(LocaleWrap.get("handler_choice_move"), LocaleWrap.get(this.name()));
    }

    public HandlerState getState() {
        return this.state;
    }

}

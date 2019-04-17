package underlings.game;

import underlings.gui.DrawChoice;
import underlings.gui.ElementGiver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Handler {

    public ElementGiver elementGiver;
    private HandlerState state;
    private HashMap<HandlerState, List<HandlerState>> allowedStates;

    public Handler(HandlerState state) {
        this.state = state;
        this.initializeHashMap();
        this.elementGiver = new ElementGiver(DrawChoice.RANDOM);
    }

    private void initializeHashMap() {
        this.allowedStates = new HashMap<>();

        this.allowedStates.put(HandlerState.READY_ROOM, createStateList(
                HandlerState.CARD,
                HandlerState.FIELD,
                HandlerState.FIELD_WHITESPACE,
                HandlerState.READY_ROOM));

        this.allowedStates.put(HandlerState.FIELD, createStateList(
                HandlerState.FIELD,
                HandlerState.BREAK_ROOM));

        this.allowedStates.put(HandlerState.CARD, createStateList(
                HandlerState.CARD,
                HandlerState.BREAK_ROOM));

        this.allowedStates.put(HandlerState.BREAK_ROOM, createStateList(HandlerState.READY_ROOM));
        this.allowedStates.put(HandlerState.FIELD_WHITESPACE, createStateList(HandlerState.BREAK_ROOM));

        this.allowedStates.put(HandlerState.INCUBATION, createStateList(
                HandlerState.INCUBATION,
                HandlerState.READY_ROOM));
    }

    public List<HandlerState> createStateList(HandlerState... handlerStates) {
        return new LinkedList<>(Arrays.asList(handlerStates));
    }

    public HandlerState getState() {
        return this.state;
    }

    public void moveToState(HandlerState state) {
        if (getPossibleStates().contains(state)) {
            this.state = state;
        }
    }

    public void moveClockwiseInField() {

    }

    public List<HandlerState> getPossibleStates() {
        return allowedStates.get(this.state);
    }
}

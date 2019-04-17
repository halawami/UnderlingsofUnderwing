package underlings.game;

import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

import java.util.*;

public class Handler {

    public ElementGiver elementGiver;
    private HandlerState state;
    private HashMap<HandlerState, List<HandlerState>> allowedStates;
	public String locationString;

    public Handler(HandlerState state) {
        this.state = state;
        this.initializeHashMap();

        Map<HandlerState, String> displayStrings = new HashMap<>();
        displayStrings.put(HandlerState.READY_ROOM, "in Ready Room");
        displayStrings.put(HandlerState.BREAK_ROOM, "in Break Room");
        displayStrings.put(HandlerState.INCUBATION, "in Incubation");
        displayStrings.put(HandlerState.CARD, "on Card");

        this.elementGiver = new ElementGiver("Handler " + displayStrings.get(state), DrawChoice.RANDOM);
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
    
    @Override
    public String toString() {
    	return elementGiver.toString();
    }
}

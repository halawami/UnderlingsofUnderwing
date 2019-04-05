package underlings.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	private HandlerState state;
	private final HashMap<HandlerState, List<HandlerState>> allowedStates = initializeHashMap();

	public Handler(HandlerState state) {
		this.state = state;
	}

	public HashMap<HandlerState, List<HandlerState>> initializeHashMap() {
		HashMap<HandlerState, List<HandlerState>> toReturn = new HashMap<>();
		toReturn.put(HandlerState.READY_ROOM, createStateList(
				HandlerState.CARD, 
				HandlerState.FIELD,
				HandlerState.FIELD_WHITESPACE, 
				HandlerState.READY_ROOM));
		
		toReturn.put(HandlerState.FIELD, createStateList(
				HandlerState.FIELD,
				HandlerState.BREAK_ROOM));
		
		toReturn.put(HandlerState.CARD, createStateList(
				HandlerState.CARD, 
				HandlerState.BREAK_ROOM));
		
		toReturn.put(HandlerState.BREAK_ROOM, createStateList(HandlerState.READY_ROOM));
		toReturn.put(HandlerState.FIELD_WHITESPACE, createStateList(HandlerState.BREAK_ROOM));
		
		toReturn.put(HandlerState.INCUBATION, createStateList(
				HandlerState.INCUBATION,
				HandlerState.READY_ROOM));
		return toReturn;
	}

	public List<HandlerState> createStateList(HandlerState... handlerStates) {
		return new LinkedList<>(Arrays.asList(handlerStates));
	}

	public HandlerState getState() {
		return this.state;
	}

	public void moveToState(HandlerState state){
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

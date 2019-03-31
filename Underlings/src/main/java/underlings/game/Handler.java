package underlings.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	private HandlerState state;
	private final static HashMap<HandlerState, List<HandlerState>> allowedStates = initializeHashMap();

	public Handler() {
		this.state = HandlerState.READY_ROOM;
	}

	// had to add this because we can't go from ready room to incubation
	public Handler(HandlerState state) {
		this.state = state;
	}

	private static HashMap<HandlerState, List<HandlerState>> initializeHashMap() {
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

	private static List<HandlerState> createStateList(HandlerState... handlerStates) {
		return new LinkedList<>(Arrays.asList(handlerStates));
	}

	public HandlerState getState() {
		return this.state;
	}

	public void moveToCard() {
		moveToState(HandlerState.CARD);
	}

	public void moveToField() {
		moveToState(HandlerState.FIELD);
	}

	public void moveToFieldWhite() {
		moveToState(HandlerState.FIELD_WHITESPACE);
	}

	public void moveToReadyRoom() {
		moveToState(HandlerState.READY_ROOM);
	}

	public void moveToBreakRoom() {
		moveToState(HandlerState.BREAK_ROOM);
	}

	public void moveToIncubation() {
		moveToState(HandlerState.INCUBATION);
	}

	private void moveToState(HandlerState state){
		if (allowedStates.get(this.state).contains(state)) {
			this.state = state;
		}
	}
	
	public void moveClockwiseInField() {

	}

}

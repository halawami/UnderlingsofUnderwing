package underlings.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	private HandlerState state;
	private final static HashMap<HandlerState, List<HandlerState>> allowedStates = initializeHashMap();
	
	public Handler(){
		this.state = HandlerState.READY_ROOM;
	}
	
	private static HashMap<HandlerState, List<HandlerState>> initializeHashMap() {
		HashMap<HandlerState, List<HandlerState>> toReturn = new HashMap<>();
		toReturn.put(HandlerState.READY_ROOM, new LinkedList<HandlerState>(){{
			add(HandlerState.CARD);
			add(HandlerState.FIELD);
			add(HandlerState.FIELD_WHITESPACE);
			add(HandlerState.READY_ROOM);
		}});
		// can field go to whitespace?
		toReturn.put(HandlerState.FIELD, new LinkedList<HandlerState>(){{
			add(HandlerState.FIELD);
			add(HandlerState.BREAK_ROOM);
		}});
		toReturn.put(HandlerState.CARD, new LinkedList<HandlerState>(){{
			add(HandlerState.CARD);
			add(HandlerState.BREAK_ROOM);
			// should I put ready_room from the effect?
		}});
		toReturn.put(HandlerState.BREAK_ROOM, new LinkedList<HandlerState>(){{
			add(HandlerState.READY_ROOM);
		}});
		toReturn.put(HandlerState.FIELD_WHITESPACE, new LinkedList<HandlerState>(){{
			add(HandlerState.BREAK_ROOM);
		}});
		return toReturn;
	}

	public HandlerState getState() {
		return this.state;
	}

	public void moveToCard() {
		if(allowedStates.get(this.state).contains(HandlerState.CARD)){
			this.state = HandlerState.CARD;
		}
	}

	public void moveToField() {
		if(allowedStates.get(this.state).contains(HandlerState.FIELD)){
			this.state = HandlerState.FIELD;
		}
	}
	
	public void moveToFieldWhite(){
		if(allowedStates.get(this.state).contains(HandlerState.FIELD_WHITESPACE)){
			this.state = HandlerState.FIELD_WHITESPACE;
		}
	}

	public void moveToReadyRoom() {
		if(allowedStates.get(this.state).contains(HandlerState.READY_ROOM)){
			this.state = HandlerState.READY_ROOM;
		}
	}

	public void moveToBreakRoom() {
		if(allowedStates.get(this.state).contains(HandlerState.BREAK_ROOM)){
			this.state = HandlerState.BREAK_ROOM;
		}
	}

	public void moveToIncubation() {
		
	}

	public void moveClockwiseInField() {
		
	}

}

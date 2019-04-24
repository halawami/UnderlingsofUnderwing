package underlings.handler;

import java.util.Arrays;
import java.util.List;

public enum HandlerState {
	READY_ROOM, CARD, FIELD, FIELD_WHITESPACE, BREAK_ROOM, INCUBATION;

	static {
		READY_ROOM.displayString = "in Ready Room";
		READY_ROOM.canMoveTo(READY_ROOM, CARD, FIELD, FIELD_WHITESPACE);

		CARD.displayString = "on Card";
		CARD.canMoveTo(CARD, BREAK_ROOM);

		FIELD.displayString = null;
		FIELD.canMoveTo(FIELD, BREAK_ROOM);

		FIELD_WHITESPACE.displayString = null;
		FIELD_WHITESPACE.canMoveTo(BREAK_ROOM);

		BREAK_ROOM.displayString = "in Break Room";
		BREAK_ROOM.canMoveTo(READY_ROOM);

		INCUBATION.displayString = "in Incubation";
		INCUBATION.canMoveTo(INCUBATION, READY_ROOM);
	}

	public List<HandlerState> allowedStates;
	public String displayString;

	public void canMoveTo(HandlerState... allowedStates) {
		this.allowedStates = Arrays.asList(allowedStates);
	}

}
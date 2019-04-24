package underlings.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import underlings.gui.Choice;

public enum HandlerChoice implements Choice {

	STAY, FIELD_WHITESPACE, FIELD, CARD, READY_ROOM, BREAK_ROOM;

	private static HashMap<HandlerState, List<HandlerChoice>> movementChoices = new HashMap<>();

	static {
		canMoveTo(HandlerState.READY_ROOM, STAY, FIELD_WHITESPACE, FIELD, CARD);
		canMoveTo(HandlerState.BREAK_ROOM, READY_ROOM);
		canMoveTo(HandlerState.FIELD_WHITESPACE, BREAK_ROOM);
		canMoveTo(HandlerState.FIELD, STAY, BREAK_ROOM);
		canMoveTo(HandlerState.INCUBATION, STAY);
		canMoveTo(HandlerState.CARD, STAY, BREAK_ROOM);
	}

	private static void canMoveTo(HandlerState handlerState, HandlerChoice... choices) {
		movementChoices.put(handlerState, Arrays.asList(choices));
	}

	public static List<HandlerChoice> getMovements(HandlerState handlerState) {
		return movementChoices.get(handlerState);
	}

}

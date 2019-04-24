package underlings.handler;

import java.util.List;

import underlings.element.ElementGiver;
import underlings.gui.Choice;
import underlings.gui.DrawChoice;

public class Handler implements Choice {

	public ElementGiver elementGiver;
	private HandlerState state;

	public Handler(HandlerState state) {
		this.state = state;
		this.elementGiver = new ElementGiver("Handler " + state.displayString, DrawChoice.RANDOM);
	}

	public HandlerState getState() {
		return this.state;
	}

	public void moveToState(HandlerState state) {
		this.state = state;
	}
	
	public List<HandlerState> getPossibleStates() {
		return this.state.allowedStates;
	}

	public List<HandlerChoice> getPossibleChoices() {
		return HandlerChoice.getMovements(this.state);
	}

	@Override
	public String toString() {
		return this.elementGiver.toString();
	}

	public void setLocation(String location) {
		this.elementGiver.display = "Handler on " + location;
	}
}

package underlings.game;

import java.util.List;

import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

public class Handler {

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

	@Override
	public String toString() {
		return this.elementGiver.toString();
	}

	public void setLocation(String location) {
		this.elementGiver.display = "Handler on " + location;
	}
}

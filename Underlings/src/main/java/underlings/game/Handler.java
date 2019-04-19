package underlings.game;

import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

import java.util.*;

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
        if (getPossibleStates().contains(state)) {
            this.state = state;
        }
    }

    public void moveClockwiseInField() {

    }

    public List<HandlerState> getPossibleStates() {
        return this.state.allowedStates;
    }
    
    @Override
    public String toString() {
    	return elementGiver.toString();
    }

	public void setLocation(String location) {
		this.elementGiver.display = "Handler on "+location;
	}
}

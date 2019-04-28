package underlings.handler;

import java.util.List;
import underlings.element.ElementGiver;
import underlings.gui.Choice;
import underlings.gui.DrawChoice;

public class Handler implements Choice {

    public ElementGiver elementGiver;
    private HandlerState state;
    private String location;

    public Handler(HandlerState state) {
        this.moveToState(state);
    }

    public HandlerState getState() {
        return this.state;
    }

    public void moveToState(HandlerState state) {
        this.state = state;
        this.elementGiver = new ElementGiver("Handler " + state.displayString, DrawChoice.RANDOM);
    }

    public List<HandlerState> getPossibleStates() {
        return this.state.allowedStates;
    }

    public List<HandlerChoice> getPossibleChoices() {
        return HandlerChoice.getMovements(this.state);
    }

    @Override
    public String toString() {
        if (this.state == HandlerState.CARD || this.state == HandlerState.FIELD
                || this.state == HandlerState.FIELD_WHITESPACE) {
            return "Handler on " + this.location;
        }
        return "Handler " + this.state.displayString;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

package underlings.handler;

import java.util.List;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;
import underlings.utilities.LocaleWrap;

public class Handler extends ElementGiver {

    private HandlerState state;
    private String location;

    public Handler(HandlerState state) {
        super(DrawChoice.RANDOM);
        this.moveToState(state);
    }

    public HandlerState getState() {
        return this.state;
    }

    public void moveToState(HandlerState state) {
        this.state = state;
    }

    public List<HandlerChoice> getPossibleChoices() {
        return HandlerChoice.getMovements(this.state);
    }

    @Override
    public String toString() {
        return LocaleWrap.format("HANDLER_" + this.state.name(), this.location);
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

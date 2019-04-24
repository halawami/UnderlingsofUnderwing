package underlings.game;

import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;
import underlings.handler.Handler;

public class FieldSpace {

    private DrawChoice color;

    public FieldSpace(DrawChoice color) {
        this.color = color;
    }

    public void addHandler(Handler handler) {
        handler.elementGiver = new ElementGiver("Handler on " + this.color.name() + " Field Space", DrawChoice.RANDOM, this.color);

    }
}

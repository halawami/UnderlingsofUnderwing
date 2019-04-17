package underlings.game;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

public class FieldSpace {

    private DrawChoice color;

    public FieldSpace(DrawChoice color) {
        this.color = color;
    }

    public void addHandler(Handler handler) {
        handler.elementGiver = new ElementGiver("Handler on " + this.color.toString() + " Field Space", DrawChoice.RANDOM, this.color);

    }
}

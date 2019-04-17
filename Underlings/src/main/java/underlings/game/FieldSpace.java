package underlings.game;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.gui.DrawChoice;
import underlings.gui.ElementGiver;

public class FieldSpace {

    private DrawChoice color;

    public FieldSpace(DrawChoice color) {
        this.color = color;
    }

    public void addHandler(Handler handler) {
        handler.elementGiver = new ElementGiver(DrawChoice.RANDOM, this.color);
    }
}

package underlings.field;

import java.util.ArrayList;
import java.util.List;

import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.utilities.LocaleWrap;

public class FieldSpace {

    public DrawChoice color;
    private List<Handler> handlers;

    public FieldSpace(DrawChoice color) {
        this.color = color;
        this.handlers = new ArrayList<>();
    }

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
        handler.addDrawChoice(this.color);
        handler.setLocation(LocaleWrap.get(this.color.name()));
    }

    public boolean contains(Handler handler) {
        return this.handlers.contains(handler);
    }

    public void remove(Handler handler) {
        this.handlers.remove(handler);
        handler.resetDrawChoice();
        handler.addDrawChoice(DrawChoice.RANDOM);
    }

    @Override
    public String toString() {
        return LocaleWrap.format("field_space_string", this.color);
    }

}

package underlings.field;

import java.util.ArrayList;
import java.util.List;

import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;
import underlings.handler.Handler;

public class FieldSpace {

    private DrawChoice color;
    private List<Handler> handlers;
    
    public FieldSpace(DrawChoice color) {
        this.color = color;
        this.handlers = new ArrayList<>();
    }

    public void addHandler(Handler handler) {
    	this.handlers.add(handler);
        handler.elementGiver = new ElementGiver("Handler on " + this.color.name() + " Field Space", DrawChoice.RANDOM, this.color);
    }

	public boolean contains(Handler handler) {
		return this.handlers.contains(handler);
	}
	
	public void remove(Handler handler) {
		this.handlers.remove(handler);
	}
	
}

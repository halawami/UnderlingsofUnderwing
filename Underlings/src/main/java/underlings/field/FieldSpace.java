package underlings.field;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.utilities.LocaleWrap;

public class FieldSpace {

	private DrawChoice color;
	private List<Handler> handlers;

	public FieldSpace(DrawChoice color) {
		this.color = color;
		this.handlers = new ArrayList<>();
	}

	public void addHandler(Handler handler) {
		this.handlers.add(handler);
		handler.setLocation(MessageFormat.format(LocaleWrap.get("field_space_color"), this.color));
		handler.elementGiver = new ElementGiver(
				MessageFormat.format(LocaleWrap.get("field_space_element_giver"), this.color), DrawChoice.RANDOM,
				this.color);
	}

	public boolean contains(Handler handler) {
		return this.handlers.contains(handler);
	}

	public void remove(Handler handler) {
		this.handlers.remove(handler);
	}

}

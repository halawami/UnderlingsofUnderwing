package underlings.element;

import java.util.Arrays;
import java.util.List;

import underlings.gui.DrawChoice;

public class ElementGiver {

	public List<DrawChoice> drawChoices;
	public String display;

	public ElementGiver(String display, DrawChoice ... drawChoices) {
		this.drawChoices = Arrays.asList(drawChoices);
		this.display = display;
	}

	@Override
	public String toString() {
		return this.display;
	}
}

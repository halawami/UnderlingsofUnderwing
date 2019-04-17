package underlings.element;

import underlings.element.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import underlings.gui.ElementDrawChoice;

public class ElementGiver {

	public List<DrawChoice> drawChoices;

	public ElementGiver(DrawChoice ... drawChoices) {
		this.drawChoices = Arrays.asList(drawChoices);
	}

	@Override
	public String toString() {
		return "Handler";
	}

	public List<ElementDrawChoice> getElementDrawChoices(){
		return null;
	}
}

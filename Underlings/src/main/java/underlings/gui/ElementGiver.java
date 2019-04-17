package underlings.gui;

import underlings.element.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

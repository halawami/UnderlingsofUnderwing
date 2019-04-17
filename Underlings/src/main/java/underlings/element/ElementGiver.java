package underlings.element;

import java.util.ArrayList;
import java.util.List;

import underlings.gui.ElementDrawChoice;

public class ElementGiver {

	public List<ElementDrawChoice> getElementDrawChoices() {
		List<ElementDrawChoice> drawChoices = new ArrayList<>();
		drawChoices.add(new ElementDrawChoice());
		return drawChoices;
	}
	
	@Override
	public String toString() {
		return "Handler";
	}

}

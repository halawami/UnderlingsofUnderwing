package underlings.gui;

import underlings.element.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementGiver {
	
	@Override
	public String toString() {
		return "Handler";
	}

	public List<DrawChoice> getDrawChoices() {
		List<DrawChoice> drawChoices = new ArrayList<>();
		drawChoices.add(DrawChoice.RANDOM);
		return drawChoices;
	}

	public List<ElementDrawChoice> getElementDrawChoices(){
		return null;
	}
}

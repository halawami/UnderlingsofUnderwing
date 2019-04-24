package underlings.element;

import java.util.ArrayList;
import java.util.List;

import underlings.gui.Choice;

public class ElementSpace implements Choice {

	public List<ElementColor> elements;
	public ElementColor color;
	public ElementSpacePosition position;

	public ElementSpace(){
		this.elements = new ArrayList<>();
	}

	public ElementSpace(ElementColor color) {
		this();
		this.color = color;
	}

	public void addElements(Element... elementsToAdd) {
		for (Element element : elementsToAdd) {
			this.elements.add(element.getColor());
		}
	}
	
	@Override
	public String toString() {
		return color + " Element Space";
	}

}

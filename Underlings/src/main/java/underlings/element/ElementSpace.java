package underlings.element;

import java.util.ArrayList;
import java.util.List;

public class ElementSpace {

	public List<ElementColor> elements;
	public ElementColor color;

	public ElementSpace(ElementColor color) {
		this.color = color;
		this.elements = new ArrayList<>();
	}

	public void addElements(Element... elementsToAdd) {
		for (Element element : elementsToAdd) {
			this.elements.add(element.getColor());
		}
	}

}

package underlings.element;

import java.util.ArrayList;
import java.util.List;

public class ElementSpace {

	private boolean complete = false;
	private List<ElementColor> elements;
	private ElementColor color;

	public ElementSpace(ElementColor color) {
		this.color = color;
		this.elements = new ArrayList<>();
	}

	public void addElements(Element... elementsToAdd) {
		for (Element element : elementsToAdd) {
			this.elements.add(element.getColor());
		}
	}

	public boolean isComplete() {

		switch (this.color) {
		case ORANGE:
			return (this.hasElement(ElementColor.ORANGE)
					|| (this.hasElement(ElementColor.RED) && this.hasElement(ElementColor.YELLOW)));
		case RED:
			return (this.hasElement(ElementColor.RED));
		case BLACK:
			return (this.hasElement(ElementColor.BLACK));
		case GREEN:
			return (this.hasElement(ElementColor.GREEN));
		case BLUE:
			return (this.hasElement(ElementColor.BLUE));
		case WHITE:
			return (this.hasElement(ElementColor.WHITE));
		case YELLOW:
			return (this.hasElement(ElementColor.YELLOW));
		case PURPLE:
			return (this.hasElement(ElementColor.PURPLE));
		default:
			return this.complete;
		}

	}

	public boolean hasElement(ElementColor elementColor) {
		return this.elements.contains(elementColor);
	}

}

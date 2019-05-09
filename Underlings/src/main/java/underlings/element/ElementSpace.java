package underlings.element;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import underlings.gui.Choice;
import underlings.utilities.LocaleWrap;

public class ElementSpace implements Choice {

	public List<ElementColor> elements;
	public ElementColor color;
	public ElementSpacePosition position;

	public ElementSpace() {
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

	public void destroyAllElementsOfColor(ElementColor colorOfElementsToDestroy) {
		this.elements.removeIf(colorOfElementsToDestroy::equals);
	}

	public void destroyOneElementOfColor(ElementColor colorOfElementsToDestroy) {
		this.elements.remove(colorOfElementsToDestroy);
	}

	@Override
	public String toString() {
		return MessageFormat.format(LocaleWrap.get("element_space"), this.color);
	}

}

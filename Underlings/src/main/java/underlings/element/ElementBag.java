package underlings.element;

public class ElementBag {

	public Element drawElementFromList(ElementColor... colors) {
		return new Element(colors[0]);
	}

}

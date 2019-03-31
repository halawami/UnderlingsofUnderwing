package underlings.element;

import java.util.HashMap;
import java.util.Map;

public class ElementBag {

	Map<ElementColor, Integer> elementCount;
	
	public ElementBag() {
		this.elementCount = new HashMap<>();
		this.elementCount.put(ElementColor.BLUE, 20);
		this.elementCount.put(ElementColor.RED, 20);
		this.elementCount.put(ElementColor.YELLOW, 20);
		this.elementCount.put(ElementColor.PURPLE, 10);
		this.elementCount.put(ElementColor.GREEN, 10);
		this.elementCount.put(ElementColor.ORANGE, 10);
		this.elementCount.put(ElementColor.WHITE, 5);
		this.elementCount.put(ElementColor.BLACK, 5);
	}
	
	public Element drawElementFromList(ElementColor... colors) {
		return new Element(colors[0]);
	}

	public int getNumberRemaining(ElementColor color) {
		return this.elementCount.get(color);
	}

}
	
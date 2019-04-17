package underlings.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ElementBag {

	private Map<ElementColor, Integer> elementCount;
	private ElementFactory elementFactory;
	private Random random;
	
	public ElementBag(ElementFactory elementFactory, Random random) {
		this.elementFactory = elementFactory;
		this.random = random;
		
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
	
	public Element drawElementFromList(ElementColor... colors)  {
		
		ArrayList<ElementColor> possibilities = new ArrayList<>();
		
		for (ElementColor color : colors) {
			for (int numberRemaining = 0; numberRemaining < this.elementCount.get(color); numberRemaining++) {
				possibilities.add(color);
			}
		}
		
		int selectionNumber = this.random.nextInt(possibilities.size());
		ElementColor selectedElement = possibilities.get(selectionNumber);
		
		
		this.elementCount.put(selectedElement, this.elementCount.get(selectedElement) - 1);
		
		return this.elementFactory.createElement(selectedElement);
	}

	public int getNumberRemaining(ElementColor color) {
		return this.elementCount.get(color);
	}

	public Element drawRandomElement() {
		return this.drawElementFromList(ElementColor.BLUE, ElementColor.RED,
				ElementColor.YELLOW, ElementColor.PURPLE, ElementColor.GREEN, ElementColor.ORANGE, ElementColor.WHITE,
				ElementColor.BLACK);
	}

}
	
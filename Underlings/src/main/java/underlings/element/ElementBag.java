package underlings.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import underlings.gui.DrawChoice;

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

	public Element drawRandomPrimaryElement() {
		return this.drawElementFromList(ElementColor.BLUE, ElementColor.RED, ElementColor.YELLOW);
	}

	public Element drawRandomSecondayElement() {
		return this.drawElementFromList(ElementColor.PURPLE, ElementColor.GREEN, ElementColor.ORANGE);
	}

	public Element drawCoolElement() {
		return this.drawElementFromList(ElementColor.BLUE, ElementColor.GREEN, ElementColor.PURPLE);
	}

	public Element drawWarmElement() {
		return this.drawElementFromList(ElementColor.RED, ElementColor.YELLOW, ElementColor.ORANGE);
	}

	public Element drawElement(DrawChoice drawChoice) {
		switch (drawChoice) {
		case RANDOM:
			return this.drawRandomElement();
		case WARM:
			return this.drawWarmElement();
		case COOL:
			return this.drawCoolElement();
		default:
			return this.drawElementFromList(drawChoice.elementColor);
		}
	}

	// TODO: implement this
	public void putElement(ElementColor color) {
		this.elementCount.put(color, this.elementCount.get(color) + 1);
	}

}
	
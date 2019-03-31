package tests.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;

public class ElementTests {

	private ElementBag elementBag;

	@Before
	public void init() {
		ElementFactory elementFactory = new ElementFactory();
		this.elementBag = new ElementBag(elementFactory);
	}

	@Test
	public void testDrawListSingle() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(ElementColor.BLUE, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.RED);
		assertEquals(ElementColor.RED, drawnElement.getColor());
		
		drawnElement = this.elementBag.drawElementFromList(ElementColor.GREEN);
		assertEquals(ElementColor.GREEN, drawnElement.getColor());
		
		drawnElement = this.elementBag.drawElementFromList(ElementColor.YELLOW);
		assertEquals(ElementColor.YELLOW, drawnElement.getColor());
		
		drawnElement = this.elementBag.drawElementFromList(ElementColor.ORANGE);
		assertEquals(ElementColor.ORANGE, drawnElement.getColor());
		
		drawnElement = this.elementBag.drawElementFromList(ElementColor.PURPLE);
		assertEquals(ElementColor.PURPLE, drawnElement.getColor());
		
		drawnElement = this.elementBag.drawElementFromList(ElementColor.WHITE);
		assertEquals(ElementColor.WHITE, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.BLACK);
		assertEquals(ElementColor.BLACK, drawnElement.getColor());
	}

	@Test
	public void testInitNumberRemaining() {
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.YELLOW));
		assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.PURPLE));
		assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.GREEN));
		assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.ORANGE));
		assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.WHITE));
		assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.BLACK));
	}
	
	@Test 
	public void testNumberRemainingAfterDraw() {
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}

}

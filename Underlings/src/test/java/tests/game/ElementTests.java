package tests.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;

public class ElementTests {

	private ElementBag elementBag;
	
	@Before
	public void init() {
		this.elementBag = new ElementBag();
	}
	
	@Test
	public void testDrawListSingle_Blue() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(ElementColor.BLUE, drawnElement.getColor());
	}
	
	@Test
	public void testDrawListSingle_Red() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.RED);
		assertEquals(ElementColor.RED, drawnElement.getColor());
	}
	
	@Test
	public void testDrawListSingle_Green() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.GREEN);
		assertEquals(ElementColor.GREEN, drawnElement.getColor());
	}
	
	@Test
	public void testDrawListSingle_Yellow() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.YELLOW);
		assertEquals(ElementColor.YELLOW, drawnElement.getColor());
	}
	
	@Test
	public void testDrawListSingle_Orange() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.ORANGE);
		assertEquals(ElementColor.ORANGE, drawnElement.getColor());
	}
	
}

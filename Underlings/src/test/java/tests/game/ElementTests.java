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
	
	@Test
	public void testDrawListSingle_Purple() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.PURPLE);
		assertEquals(ElementColor.PURPLE, drawnElement.getColor());
	}
	
	@Test
	public void testDrawListSingle_White() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.WHITE);
		assertEquals(ElementColor.WHITE, drawnElement.getColor());
	}
	
	@Test
	public void testDrawListSingle_Black() {
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLACK);
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
	
}

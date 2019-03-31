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
	
}

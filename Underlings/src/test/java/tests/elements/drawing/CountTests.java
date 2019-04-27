package tests.elements.drawing;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;

public class CountTests {

	private ElementBag elementBag;

	@Before
	public void init() {
		ElementFactory elementFactory = new ElementFactory();
		this.elementBag = new ElementBag(elementFactory, new Random());
	}

	@Test
	public void testDraw20Left() {
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}

	@Test
	public void testDraw1Left() {
		drawElements(ElementColor.BLUE, 19);
		assertEquals(1, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(0, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}

	private void drawElements(ElementColor color, int numToDraw) {
		for (int i = 0; i < numToDraw; i++) {
			this.elementBag.drawElementFromList(color);
		}
	}
	
	@Test
	public void testPutOneBlueElement(){
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		drawElements(ElementColor.BLUE, 5);
		assertEquals(15, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		this.elementBag.putElement(ElementColor.BLUE);
		assertEquals(16, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}
	
	@Test
	public void testPutTwoBlueElement(){
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		drawElements(ElementColor.BLUE, 5);
		assertEquals(15, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		this.elementBag.putElement(ElementColor.BLUE);
		this.elementBag.putElement(ElementColor.BLUE);
		assertEquals(17, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}
	
	@Test
	public void testPutOneBlueOneRedElement(){
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		drawElements(ElementColor.BLUE, 5);
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
		drawElements(ElementColor.RED, 3);
		assertEquals(15, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		assertEquals(17, this.elementBag.getNumberRemaining(ElementColor.RED));
		this.elementBag.putElement(ElementColor.BLUE);
		this.elementBag.putElement(ElementColor.RED);
		assertEquals(16, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		assertEquals(18, this.elementBag.getNumberRemaining(ElementColor.RED));
	}

}

package underlings.elements.drawing;

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
		this.testDraw(ElementColor.BLUE, 1, 20, 19);
	}

	@Test
	public void testDraw1Left() {
		this.testDraw(ElementColor.BLUE, 19, 20, 1);
		this.testDraw(ElementColor.BLUE, 1, 1, 0);
	}

	@Test
	public void testPutOneBlueElement() {
		this.testDraw(ElementColor.BLUE, 5, 20, 15);
		this.elementBag.putElement(ElementColor.BLUE);
		assertEquals(16, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}

	@Test
	public void testPutTwoBlueElements() {
		this.testDraw(ElementColor.BLUE, 5, 20, 15);
		this.elementBag.putElement(ElementColor.BLUE);
		this.elementBag.putElement(ElementColor.BLUE);
		assertEquals(17, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}

	@Test
	public void testPutOneBlueOneRedElement() {
		this.testDraw(ElementColor.BLUE, 5, 20, 15);
		this.testDraw(ElementColor.RED, 3, 20, 17);
		assertEquals(15, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		assertEquals(17, this.elementBag.getNumberRemaining(ElementColor.RED));
		this.elementBag.putElement(ElementColor.BLUE);
		this.elementBag.putElement(ElementColor.RED);
		assertEquals(16, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		assertEquals(18, this.elementBag.getNumberRemaining(ElementColor.RED));
	}

	private void testDraw(ElementColor color, int numToDraw, int before, int after) {
		assertEquals(before, this.elementBag.getNumberRemaining(color));
		for (int i = 0; i < numToDraw; i++) {
			this.elementBag.drawElementFromList(color);
		}
		assertEquals(after, this.elementBag.getNumberRemaining(color));
	}

}

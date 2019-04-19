package tests.elementspace.single;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class DoneTests {

	@Test
	public void testEmpty() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.RED);
		assertFalse(elementSpace.isComplete());
	}

	@Test
	public void testRed() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.RED);
		Element element = new Element(ElementColor.RED);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

	@Test
	public void testGreen() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.GREEN);
		Element element = new Element(ElementColor.GREEN);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

	@Test
	public void testBlue() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.BLUE);
		Element element = new Element(ElementColor.BLUE);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

	@Test
	public void testOrange() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
		Element element = new Element(ElementColor.ORANGE);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

	@Test
	public void testYellow() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.YELLOW);
		Element element = new Element(ElementColor.YELLOW);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

	@Test
	public void testPurple() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.PURPLE);
		Element element = new Element(ElementColor.PURPLE);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

	@Test
	public void testWhite() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.WHITE);
		Element element = new Element(ElementColor.WHITE);

		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}
	
	@Test
	public void testBlack() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.BLACK);
		Element element = new Element(ElementColor.BLACK);
		
		elementSpace.addElements(element);
		assertTrue(elementSpace.isComplete());
	}

}
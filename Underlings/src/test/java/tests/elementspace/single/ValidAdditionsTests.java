package tests.elementspace.single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class ValidAdditionsTests {

	@Test
	public void testEmptyRed() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.RED);
		List<ElementColor> expected = Arrays.asList(ElementColor.RED);
		
		assertEquals(expected, ElementSpaceLogic.getValidAdditions(elementSpace));
	}
	
	@Test
	public void testEmptyBlue() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.BLUE);
		List<ElementColor> expected = Arrays.asList(ElementColor.BLUE);
		
		assertEquals(expected, ElementSpaceLogic.getValidAdditions(elementSpace));
	}

	public void testColor(ElementColor color) {
		ElementSpace elementSpace = new ElementSpace(color);
		elementSpace.addElements(new Element(color));

		assertTrue(ElementSpaceLogic.getValidAdditions(elementSpace).isEmpty());
	}

	@Test
	public void testRed() {
		testColor(ElementColor.RED);
	}

	@Test
	public void testGreen() {
		testColor(ElementColor.GREEN);
	}

	@Test
	public void testBlue() {
		testColor(ElementColor.BLUE);
	}

	@Test
	public void testOrange() {
		testColor(ElementColor.ORANGE);
	}

	@Test
	public void testYellow() {
		testColor(ElementColor.YELLOW);
	}

	@Test
	public void testPurple() {
		testColor(ElementColor.PURPLE);
	}

	@Test
	public void testWhite() {
		testColor(ElementColor.WHITE);
	}

	@Test
	public void testBlack() {
		testColor(ElementColor.BLACK);
	}

}

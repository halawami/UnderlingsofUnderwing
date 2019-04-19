package tests.elementspace.combo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class ValidAdditionsTests {

	@Test
	public void testEmptyGreen() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.GREEN);
		List<ElementColor> expected = Arrays.asList(ElementColor.GREEN, ElementColor.YELLOW, ElementColor.BLUE);
		
		List<ElementColor> actual = ElementSpaceLogic.getValidAdditions(elementSpace);
		Collections.sort(expected);
		Collections.sort(actual);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEmptyOrange() {
		ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
		List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);
		
		List<ElementColor> actual = ElementSpaceLogic.getValidAdditions(elementSpace);
		Collections.sort(expected);
		Collections.sort(actual);
		
		assertEquals(expected, actual);
	}

}

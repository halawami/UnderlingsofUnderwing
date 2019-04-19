package tests.elementspace.combo;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class BlackTests {
	
	@Test
	public void testRed() {
		ElementSpace testSpace = new ElementSpace(ElementColor.BLACK);
		testSpace.addElements(new Element(ElementColor.RED));
		assertFalse(ElementSpaceLogic.isComplete(testSpace));
	}
	
//	@Test
//	public void testBlue() {
//		this.blackElementSpace.addElements(this.blueElement);
//		assertFalse(this.blackElementSpace.isComplete());
//	}
//	
//	@Test
//	public void testYellow() {
//		this.blackElementSpace.addElements(this.yellowElement);
//		assertFalse(this.blackElementSpace.isComplete());
//	}
//	
//	@Test
//	public void testRedBlue() {
//		this.blackElementSpace.addElements(this.redElement, this.blueElement);
//		assertFalse(this.blackElementSpace.isComplete());
//	}
//	
//	@Test
//	public void testBlueYellow() {
//		this.blackElementSpace.addElements(this.blueElement, this.yellowElement);
//		assertFalse(this.blackElementSpace.isComplete());
//	}
//	
//	@Ignore
//	public void testRedBlueYellow() {
//		this.blackElementSpace.addElements(this.redElement, this.blueElement, this.yellowElement);
//		assertTrue(this.blackElementSpace.isComplete());
//	}
	
}

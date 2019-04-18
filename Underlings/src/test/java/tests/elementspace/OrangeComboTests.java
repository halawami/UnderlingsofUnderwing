package tests.elementspace;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class OrangeComboTests {

	private ElementSpace elementSpace;
	private Element redElement, yellowElement;
	
	@Before
	public void init() {
		this.elementSpace = new ElementSpace(ElementColor.ORANGE);
		this.redElement = new Element(ElementColor.RED);
		this.yellowElement = new Element(ElementColor.YELLOW);
	}
	
	@Test
	public void testRed() {
		this.elementSpace.addElements(this.redElement);
		assertFalse(this.elementSpace.isComplete());
	}
	
	@Test
	public void testYellow() {
		this.elementSpace.addElements(this.yellowElement);
		assertFalse(this.elementSpace.isComplete());
	}
	
	@Test
	public void testRedThenYellow() {	
		this.elementSpace.addElements(this.redElement);
		this.elementSpace.addElements(this.yellowElement);
		assertTrue(this.elementSpace.isComplete());
	}
	
	@Test
	public void testYellowThenRed() {
		this.elementSpace.addElements(this.yellowElement);
		this.elementSpace.addElements(this.redElement);
		assertTrue(this.elementSpace.isComplete());
	}
	
	@Test
	public void testRedAndYellow() {
		this.elementSpace.addElements(this.redElement, this.yellowElement);
		assertTrue(this.elementSpace.isComplete());
	}
	
}

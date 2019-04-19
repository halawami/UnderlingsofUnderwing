package tests.elementspace.combo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class OrangeTests {

	private ElementSpace elementSpace;
	private Element elementOne, elementTwo;
	
	private ElementColor desired = ElementColor.ORANGE;
	private ElementColor elementOneColor = ElementColor.RED;
	private ElementColor elementTwoColor = ElementColor.YELLOW;
	
	@Before
	public void init() {
		this.elementSpace = new ElementSpace(this.desired);
		this.elementOne = new Element(this.elementOneColor);
		this.elementTwo = new Element(this.elementTwoColor);
	}
	
	@Test
	public void testRed() {
		this.elementSpace.addElements(this.elementOne);
		assertFalse(this.elementSpace.isComplete());
	}
	
	@Test
	public void testYellow() {
		this.elementSpace.addElements(this.elementTwo);
		assertFalse(this.elementSpace.isComplete());
	}
	
	@Test
	public void testRedThenYellow() {	
		this.elementSpace.addElements(this.elementOne);
		this.elementSpace.addElements(this.elementTwo);
		assertTrue(this.elementSpace.isComplete());
	}
	
	@Test
	public void testYellowThenRed() {
		this.elementSpace.addElements(this.elementTwo);
		this.elementSpace.addElements(this.elementOne);
		assertTrue(this.elementSpace.isComplete());
	}
	
	@Test
	public void testRedAndYellow() {
		this.elementSpace.addElements(this.elementOne, this.elementTwo);
		assertTrue(this.elementSpace.isComplete());
	}
	
}

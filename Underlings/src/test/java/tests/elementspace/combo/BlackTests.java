package tests.elementspace.combo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class BlackTests {
	
	ElementSpace blackElementSpace;
	
	@Before
	public void init() {
		this.blackElementSpace = new ElementSpace(ElementColor.BLACK);
	}
	
	@Test
	public void testRed() {
		this.blackElementSpace.addElements(new Element(ElementColor.RED));
		assertFalse(ElementSpaceLogic.isComplete(this.blackElementSpace));
	}
	
	@Test
	public void testBlue() {
		this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
		assertFalse(ElementSpaceLogic.isComplete(this.blackElementSpace));
	}
	
	@Test
	public void testYellow() {
		this.blackElementSpace.addElements(new Element(ElementColor.YELLOW));
		assertFalse(ElementSpaceLogic.isComplete(this.blackElementSpace));
	}
	
	@Test
	public void testRedBlue() {
		this.blackElementSpace.addElements(new Element(ElementColor.RED));
		this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
		assertFalse(ElementSpaceLogic.isComplete(this.blackElementSpace));
	}
	
	@Test
	public void testBlueYellow() {
		this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
		this.blackElementSpace.addElements(new Element(ElementColor.YELLOW));
		assertFalse(ElementSpaceLogic.isComplete(this.blackElementSpace));
	}
	
	@Test
	public void testRedBlueYellow() {
		this.blackElementSpace.addElements(new Element(ElementColor.RED));
		this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
		this.blackElementSpace.addElements(new Element(ElementColor.YELLOW));
		assertTrue(ElementSpaceLogic.isComplete(this.blackElementSpace));
	}
	
}

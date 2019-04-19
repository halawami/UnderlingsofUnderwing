package tests.elementspace.combo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import underlings.element.BlackElementSpace;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class BlackTests {

	private Element redElement;
	private Element blueElement;
	private Element yellowElement;
	
	private ElementSpace blackElementSpace;
	
	@Before
	public void init() {
		this.blackElementSpace = new BlackElementSpace();
		this.redElement = new Element(ElementColor.RED);
		this.blueElement = new Element(ElementColor.BLUE);
		this.yellowElement = new Element(ElementColor.YELLOW);
	}
	
	@Test
	public void testRed() {
		this.blackElementSpace.addElements(this.redElement);
		assertFalse(ElementSpaceLogic.isComplete(this.blackElementSpace));
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

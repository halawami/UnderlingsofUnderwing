package tests.elements.drawing;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;
import underlings.gui.DrawChoice;

public class DrawChoiceTests {
	
	Random random;
	ElementBag elementBag;
	
	@Before
	public void init() {
		this.random = EasyMock.mock(Random.class);
		EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andReturn(1);
		EasyMock.replay(this.random);
		
		this.elementBag = new ElementBag(new ElementFactory(), this.random);
	}
	
	@Test
	public void testRandom() {
		this.testDrawChoice(DrawChoice.RANDOM, ElementColor.BLUE);
	}
	
	@Test
	public void testWarm() {
		this.testDrawChoice(DrawChoice.WARM, ElementColor.RED);
	}
	
	@Test
	public void testCool() {
		this.testDrawChoice(DrawChoice.COOL, ElementColor.BLUE);
	}
	
	@Test
	public void testRed() {
		this.testDrawChoice(DrawChoice.RED, ElementColor.RED);
	}
	
	private void testDrawChoice(DrawChoice drawChoice, ElementColor color) {
		Element element = this.elementBag.drawElement(drawChoice);
		
		EasyMock.verify(this.random);
		assertEquals(color, element.getColor());
	}
	
}

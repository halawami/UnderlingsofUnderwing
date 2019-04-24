package tests.elements.drawing;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;
import underlings.gui.DrawChoice;

public class DrawChoiceTests {

	@Test
	public void testRandom() {
		Random random = EasyMock.mock(Random.class);
		EasyMock.expect(random.nextInt(EasyMock.anyInt())).andReturn(1);
		EasyMock.replay(random);
		
		ElementBag elementBag = new ElementBag(new ElementFactory(), random);
		
		Element element = elementBag.drawElement(DrawChoice.RANDOM);
		
		EasyMock.verify(random);
		assertEquals(ElementColor.BLUE, element.getColor());
	}
	
	@Test
	public void testWarm() {
		Random random = EasyMock.mock(Random.class);
		EasyMock.expect(random.nextInt(EasyMock.anyInt())).andReturn(1);
		EasyMock.replay(random);
		
		ElementBag elementBag = new ElementBag(new ElementFactory(), random);
		
		Element element = elementBag.drawElement(DrawChoice.WARM);
		
		EasyMock.verify(random);
		assertEquals(ElementColor.RED, element.getColor());
	}
	
}

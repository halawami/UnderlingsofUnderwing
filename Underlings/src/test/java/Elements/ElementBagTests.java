package Elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class ElementBagTests {

	@Test
	public void testGetSpecifiedColor() {
		ElementBag bag = ElementBag.getInstance();
		
		Element element = bag.getSpecifiedColor(ElementColor.BLUE);
		
		assertEquals(element.getColor(), ElementColor.BLUE);
	}

}

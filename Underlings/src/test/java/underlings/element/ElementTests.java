package underlings.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ElementTests {

	@Test
	public void testToString() {
		assertEquals("Red", this.getString(ElementColor.RED));
		assertEquals("Blue", this.getString(ElementColor.BLUE));
		assertEquals("Yellow", this.getString(ElementColor.YELLOW));
		assertEquals("Orange", this.getString(ElementColor.ORANGE));
		assertEquals("Purple", this.getString(ElementColor.PURPLE));
		assertEquals("Green", this.getString(ElementColor.GREEN));
		assertEquals("Black", this.getString(ElementColor.BLACK));
		assertEquals("White", this.getString(ElementColor.WHITE));
	}

	private String getString(ElementColor color) {
		Element element = new Element(color);
		return element.toString();
	}
}

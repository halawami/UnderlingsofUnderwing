package underlings.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ElementTests {

    @Test
    public void testToStringRed() {
        Element element = new Element(ElementColor.RED);
        assertEquals(ElementColor.RED.toString(), element.toString());
    }

    @Test
    public void testToStringBlue() {
        Element element = new Element(ElementColor.BLUE);
        assertEquals(ElementColor.BLUE.toString(), element.toString());
    }
}

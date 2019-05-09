package underlings.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ElementSpaceTests {

    @Test
    public void testToStringRed() {
        ElementSpace space = new ElementSpace(ElementColor.RED);
        assertEquals("Red Element Space", space.toString());
    }

    @Test
    public void testToStringBlue() {
        ElementSpace space = new ElementSpace(ElementColor.BLUE);
        assertEquals("Blue Element Space", space.toString());
    }
}

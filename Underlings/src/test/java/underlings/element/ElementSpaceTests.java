package underlings.element;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testGetColorsEmpty() {
        ElementSpace space = new ElementSpace(ElementColor.BLUE);

        List<ElementColor> expected = Arrays.asList();
        assertEquals(expected, space.getElementColors());
    }

    @Test
    public void testGetColorsNotEmpty() {
        ElementSpace space = new ElementSpace(ElementColor.BLUE);
        space.addElements(new Element(ElementColor.BLUE));

        List<ElementColor> expected = Arrays.asList(ElementColor.BLUE);
        assertEquals(expected, space.getElementColors());
    }
}

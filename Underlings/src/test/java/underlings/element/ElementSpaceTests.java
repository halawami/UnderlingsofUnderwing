package underlings.element;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ElementSpaceTests {

    @Test
    public void testToString() {
        assertEquals("Red Element Space", this.getString(ElementColor.RED));
        assertEquals("Blue Element Space", this.getString(ElementColor.BLUE));
        assertEquals("Yellow Element Space", this.getString(ElementColor.YELLOW));
        assertEquals("Orange Element Space", this.getString(ElementColor.ORANGE));
        assertEquals("Purple Element Space", this.getString(ElementColor.PURPLE));
        assertEquals("Green Element Space", this.getString(ElementColor.GREEN));
        assertEquals("Black Element Space", this.getString(ElementColor.BLACK));
        assertEquals("White Element Space", this.getString(ElementColor.WHITE));
    }

    private String getString(ElementColor color) {
        ElementSpace space = new ElementSpace(color);
        return space.toString();
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

    @Test
    public void testGetColorsNotEmptyAlias() {
        Element element = new Element(ElementColor.RED);
        element.setAlias(ElementColor.BLUE);

        ElementSpace space = new ElementSpace(ElementColor.BLUE);
        space.addElements(element);

        List<ElementColor> expected = Arrays.asList(ElementColor.BLUE);
        assertEquals(expected, space.getElementColors());
    }
}

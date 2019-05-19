package underlings.element;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.utilities.LocaleUtilities;

public class ElementSpaceTests {

    @Test
    public void testToString() {
        assertEquals(LocaleUtilities.format("element_space", ElementColor.RED), this.getString(ElementColor.RED));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.BLUE), this.getString(ElementColor.BLUE));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.YELLOW), this.getString(ElementColor.YELLOW));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.ORANGE), this.getString(ElementColor.ORANGE));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.PURPLE), this.getString(ElementColor.PURPLE));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.GREEN), this.getString(ElementColor.GREEN));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.BLACK), this.getString(ElementColor.BLACK));
        assertEquals(LocaleUtilities.format("element_space", ElementColor.WHITE), this.getString(ElementColor.WHITE));
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

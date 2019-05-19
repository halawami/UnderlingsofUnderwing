package underlings.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ElementAliasTests {

    @Test
    public void testNoChangeBlue() {
        Element element = new Element(ElementColor.BLUE);
        element.setAlias(ElementColor.BLUE);

        assertEquals(ElementColor.BLUE, element.getAlias());
    }

    @Test
    public void testNoChangeRed() {
        Element element = new Element(ElementColor.RED);
        element.setAlias(ElementColor.RED);

        assertEquals(ElementColor.RED, element.getAlias());
    }

    @Test
    public void testInitial() {
        assertEquals(ElementColor.BLUE, (new Element(ElementColor.BLUE)).getAlias());
        assertEquals(ElementColor.RED, (new Element(ElementColor.RED)).getAlias());
    }

    @Test
    public void testChangeBlueToRed() {
        Element element = new Element(ElementColor.BLUE);
        element.setAlias(ElementColor.RED);

        assertEquals(ElementColor.RED, element.getAlias());
    }

    @Test
    public void testChangeRedToBlue() {
        Element element = new Element(ElementColor.RED);
        element.setAlias(ElementColor.BLUE);

        assertEquals(ElementColor.BLUE, element.getAlias());
    }
}
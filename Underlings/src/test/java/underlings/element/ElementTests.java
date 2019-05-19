package underlings.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.utilities.LocaleUtilities;

public class ElementTests {

    @Test
    public void testToString() {
        assertEquals(LocaleUtilities.get("RED"), this.getString(ElementColor.RED));
        assertEquals(LocaleUtilities.get("BLUE"), this.getString(ElementColor.BLUE));
        assertEquals(LocaleUtilities.get("YELLOW"), this.getString(ElementColor.YELLOW));
        assertEquals(LocaleUtilities.get("ORANGE"), this.getString(ElementColor.ORANGE));
        assertEquals(LocaleUtilities.get("PURPLE"), this.getString(ElementColor.PURPLE));
        assertEquals(LocaleUtilities.get("GREEN"), this.getString(ElementColor.GREEN));
        assertEquals(LocaleUtilities.get("BLACK"), this.getString(ElementColor.BLACK));
        assertEquals(LocaleUtilities.get("WHITE"), this.getString(ElementColor.WHITE));
    }

    private String getString(ElementColor color) {
        Element element = new Element(color);
        return element.toString();
    }
}

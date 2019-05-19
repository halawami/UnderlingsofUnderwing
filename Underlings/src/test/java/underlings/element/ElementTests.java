package underlings.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.utilities.LocaleWrap;

public class ElementTests {

    @Test
    public void testToString() {
        assertEquals(LocaleWrap.get("RED"), this.getString(ElementColor.RED));
        assertEquals(LocaleWrap.get("BLUE"), this.getString(ElementColor.BLUE));
        assertEquals(LocaleWrap.get("YELLOW"), this.getString(ElementColor.YELLOW));
        assertEquals(LocaleWrap.get("ORANGE"), this.getString(ElementColor.ORANGE));
        assertEquals(LocaleWrap.get("PURPLE"), this.getString(ElementColor.PURPLE));
        assertEquals(LocaleWrap.get("GREEN"), this.getString(ElementColor.GREEN));
        assertEquals(LocaleWrap.get("BLACK"), this.getString(ElementColor.BLACK));
        assertEquals(LocaleWrap.get("WHITE"), this.getString(ElementColor.WHITE));
    }

    private String getString(ElementColor color) {
        Element element = new Element(color);
        return element.toString();
    }
}

package underlings.element;

import org.junit.Test;

public class ElementAliasTests {

    @Test
    public void testNoChange() {
        Element element = new Element(ElementColor.BLUE);
        element.setAlias(ElementColor.BLUE);

        assertEquals(ElementColor.BLUE, element.getAlias());
    }
}

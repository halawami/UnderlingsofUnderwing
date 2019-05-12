package underlings.elementspace.destroy;


import org.junit.Assert;
import org.junit.Test;

import underlings.element.ElementSpace;

public class DestroyAllElementsTests {

    @Test
    public void testNoElements() {
        ElementSpace elementSpace = new ElementSpace();

        elementSpace.destroyAllElements();

        Assert.assertEquals(0, elementSpace.elements.size());
    }


}

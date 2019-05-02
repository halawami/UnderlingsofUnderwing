package tests.elementspace.destroy;

import org.junit.Assert;
import org.junit.Test;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class DestroyAllElementsOfColorTests {

    @Test
    public void testNoDestroyableElements() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(ElementColor.BLUE);

        elementSpace.destroyAllElementsOfColor(ElementColor.RED);

        Assert.assertEquals(1, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0));
    }
}

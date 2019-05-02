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
        elementSpace.elements.add(ElementColor.RED);

        elementSpace.destroyAllElementsOfColor(ElementColor.GREEN);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0));
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(1));
    }

    @Test
    public void testOneDestroyableElementsFirst() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(ElementColor.BLUE);
        elementSpace.elements.add(ElementColor.RED);
        elementSpace.elements.add(ElementColor.YELLOW);

        elementSpace.destroyAllElementsOfColor(ElementColor.BLUE);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(0));
        Assert.assertEquals(ElementColor.YELLOW, elementSpace.elements.get(1));
    }

    @Test
    public void testOneDestroyableElementsMiddle() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(ElementColor.BLUE);
        elementSpace.elements.add(ElementColor.RED);
        elementSpace.elements.add(ElementColor.YELLOW);

        elementSpace.destroyAllElementsOfColor(ElementColor.RED);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0));
        Assert.assertEquals(ElementColor.YELLOW, elementSpace.elements.get(1));
    }

    @Test
    public void testOneDestroyableElementsLast() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(ElementColor.BLUE);
        elementSpace.elements.add(ElementColor.RED);
        elementSpace.elements.add(ElementColor.YELLOW);

        elementSpace.destroyAllElementsOfColor(ElementColor.YELLOW);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0));
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(1));
    }
}

package underlings.element;

import org.junit.Assert;
import org.junit.Test;

public class DestroyOneElementOfColorTests {

    @Test
    public void testNoDestroyableElements() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.BLUE));
        elementSpace.elements.add(new Element(ElementColor.RED));

        elementSpace.destroyOneElementOfColor(ElementColor.GREEN);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0).getColor());
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(1).getColor());
    }

    @Test
    public void testOneDestroyableElementsFirst() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.BLUE));
        elementSpace.elements.add(new Element(ElementColor.RED));
        elementSpace.elements.add(new Element(ElementColor.YELLOW));

        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(0).getColor());
        Assert.assertEquals(ElementColor.YELLOW, elementSpace.elements.get(1).getColor());
    }

    @Test
    public void testOneDestroyableElementsMiddle() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.BLUE));
        elementSpace.elements.add(new Element(ElementColor.RED));
        elementSpace.elements.add(new Element(ElementColor.YELLOW));

        elementSpace.destroyOneElementOfColor(ElementColor.RED);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0).getColor());
        Assert.assertEquals(ElementColor.YELLOW, elementSpace.elements.get(1).getColor());
    }

    @Test
    public void testOneDestroyableElementsLast() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.BLUE));
        elementSpace.elements.add(new Element(ElementColor.RED));
        elementSpace.elements.add(new Element(ElementColor.YELLOW));

        elementSpace.destroyOneElementOfColor(ElementColor.YELLOW);

        Assert.assertEquals(2, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(0).getColor());
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(1).getColor());
    }

    @Test
    public void testMultipleDestroyableElements() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.BLUE));
        elementSpace.elements.add(new Element(ElementColor.RED));
        elementSpace.elements.add(new Element(ElementColor.BLUE));
        elementSpace.elements.add(new Element(ElementColor.YELLOW));

        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        Assert.assertEquals(3, elementSpace.elements.size());
        Assert.assertEquals(ElementColor.RED, elementSpace.elements.get(0).getColor());
        Assert.assertEquals(ElementColor.BLUE, elementSpace.elements.get(1).getColor());
        Assert.assertEquals(ElementColor.YELLOW, elementSpace.elements.get(2).getColor());
    }
}

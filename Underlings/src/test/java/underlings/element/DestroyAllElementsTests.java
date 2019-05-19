package underlings.element;

import org.junit.Assert;
import org.junit.Test;

public class DestroyAllElementsTests {

    @Test
    public void testNoElements() {
        ElementSpace elementSpace = new ElementSpace();

        elementSpace.destroyAllElements();

        Assert.assertEquals(0, elementSpace.elements.size());
    }

    @Test
    public void testOneElement() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.BLUE));

        elementSpace.destroyAllElements();

        Assert.assertEquals(0, elementSpace.elements.size());
    }

    @Test
    public void testTwoElements() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements.add(new Element(ElementColor.RED));
        elementSpace.elements.add(new Element(ElementColor.BLUE));

        elementSpace.destroyAllElements();

        Assert.assertEquals(0, elementSpace.elements.size());
    }

}

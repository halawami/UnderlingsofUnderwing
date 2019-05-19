package underlings.element;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ElementBagSetupTests {

    private ElementBag elementBag;

    @Before
    public void init() {
        ElementFactory elementFactory = new ElementFactory();
        this.elementBag = new ElementBag(elementFactory, new Random());
    }

    @Test
    public void testElementCount() {
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.YELLOW));
        assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.PURPLE));
        assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.GREEN));
        assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.ORANGE));
        assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.WHITE));
        assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.BLACK));
    }

}
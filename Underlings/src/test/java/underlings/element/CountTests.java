package underlings.element;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class CountTests {

    private ElementBag elementBag;

    @Before
    public void init() {
        ElementFactory elementFactory = new ElementFactory();
        this.elementBag = new ElementBag(elementFactory, new Random());
    }

    @Test
    public void testElementBagInit() {
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.YELLOW));
        assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.PURPLE));
        assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.GREEN));
        assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.ORANGE));
        assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.WHITE));
        assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.BLACK));
    }

    @Test
    public void testDraw20Left() {
        this.testDraw(ElementColor.BLUE, 1, 20, 19);
    }

    @Test
    public void testDraw1Left() {
        this.testDraw(ElementColor.BLUE, 19, 20, 1);
        this.testDraw(ElementColor.BLUE, 1, 1, 0);
    }

    @Test
    public void testPutOneBlueElement() {
        this.testDraw(ElementColor.BLUE, 5, 20, 15);
        this.elementBag.putElement(ElementColor.BLUE);
        assertEquals(16, this.elementBag.getNumberRemaining(ElementColor.BLUE));
    }

    @Test
    public void testPutTwoBlueElements() {
        this.testDraw(ElementColor.BLUE, 5, 20, 15);
        this.elementBag.putElement(ElementColor.BLUE);
        this.elementBag.putElement(ElementColor.BLUE);
        assertEquals(17, this.elementBag.getNumberRemaining(ElementColor.BLUE));
    }

    @Test
    public void testPutOneBlueOneRedElement() {
        this.testDraw(ElementColor.BLUE, 5, 20, 15);
        this.testDraw(ElementColor.RED, 3, 20, 17);
        assertEquals(15, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(17, this.elementBag.getNumberRemaining(ElementColor.RED));
        this.elementBag.putElement(ElementColor.BLUE);
        this.elementBag.putElement(ElementColor.RED);
        assertEquals(16, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(18, this.elementBag.getNumberRemaining(ElementColor.RED));
    }

    private void testDraw(ElementColor color, int numToDraw, int before, int after) {
        assertEquals(before, this.elementBag.getNumberRemaining(color));
        for (int i = 0; i < numToDraw; i++) {
            this.elementBag.drawElementFromList(color);
        }
        assertEquals(after, this.elementBag.getNumberRemaining(color));
    }

}

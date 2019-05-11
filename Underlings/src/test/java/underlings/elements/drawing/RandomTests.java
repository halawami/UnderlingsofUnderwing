package underlings.elements.drawing;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;

public class RandomTests {

    private ElementBag elementBag;
    private Random random;

    @Before
    public void init() {
        ElementFactory elementFactory = new ElementFactory();
        this.random = EasyMock.createMock(Random.class);
        this.elementBag = new ElementBag(elementFactory, this.random);
    }

    private void randomMock(int returnValue) {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(returnValue);
        EasyMock.replay(this.random);
    }

    @Test
    public void testCompletlyRandomRed() {
        this.randomMock(30);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomBlue() {
        this.randomMock(10);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomYellow() {
        this.randomMock(50);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomPurple() {
        this.randomMock(65);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomGreen() {
        this.randomMock(75);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomOrange() {
        this.randomMock(85);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomBlack() {
        this.randomMock(97);
        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.BLACK, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomWhite() {
        this.randomMock(92);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.WHITE, drawnElement.getColor());
    }

    @Test
    public void testPrimaryRandomRed() {
        this.randomMock(30);

        Element drawnElement = this.elementBag.drawRandomPrimaryElement();
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testPrimaryRandomBlue() {
        this.randomMock(10);

        Element drawnElement = this.elementBag.drawRandomPrimaryElement();
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testPrimaryRandomYellow() {
        this.randomMock(50);

        Element drawnElement = this.elementBag.drawRandomPrimaryElement();
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testSecondaryRandomPurple() {
        this.randomMock(5);

        Element drawnElement = this.elementBag.drawRandomSecondayElement();
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testSecondaryRandomGreen() {
        this.randomMock(15);

        Element drawnElement = this.elementBag.drawRandomSecondayElement();
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testSecondaryRandomOrange() {
        this.randomMock(25);

        Element drawnElement = this.elementBag.drawRandomSecondayElement();
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }

    @Test
    public void testCoolRandomBlue() {
        this.randomMock(5);

        Element drawnElement = this.elementBag.drawCoolElement();
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testCoolRandomGreen() {
        this.randomMock(25);

        Element drawnElement = this.elementBag.drawCoolElement();
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testCoolRandomPurple() {
        this.randomMock(35);

        Element drawnElement = this.elementBag.drawCoolElement();
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testWarmRandomRed() {
        this.randomMock(10);

        Element drawnElement = this.elementBag.drawWarmElement();
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testWarmRandomYellow() {
        this.randomMock(25);

        Element drawnElement = this.elementBag.drawWarmElement();
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testWarmRandomOrange() {
        this.randomMock(45);

        Element drawnElement = this.elementBag.drawWarmElement();
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }
}

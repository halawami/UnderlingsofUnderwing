package tests.elements.drawing;

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

    @Test
    public void testCompletlyRandomRed() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(30);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomBlue() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(10);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomYellow() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(50);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomPurple() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(65);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomGreen() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(75);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomOrange() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(85);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomBlack() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(97);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.BLACK, drawnElement.getColor());
    }

    @Test
    public void testCompletlyRandomWhite() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(92);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomElement();
        assertEquals(ElementColor.WHITE, drawnElement.getColor());
    }

    @Test
    public void testPrimaryRandomRed() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(30);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomPrimaryElement();
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testPrimaryRandomBlue() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(10);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomPrimaryElement();
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testPrimaryRandomYellow() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(50);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomPrimaryElement();
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testSecondaryRandomPurple() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(5);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomSecondayElement();
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testSecondaryRandomGreen() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(15);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomSecondayElement();
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testSecondaryRandomOrange() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(25);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawRandomSecondayElement();
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }

    @Test
    public void testCoolRandomBlue() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(5);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawCoolElement();
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testCoolRandomGreen() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(25);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawCoolElement();
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testCoolRandomPurple() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(35);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawCoolElement();
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testWarmRandomRed() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(10);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawWarmElement();
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testWarmRandomYellow() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(25);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawWarmElement();
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testWarmRandomOrange() {
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(45);
        EasyMock.replay(this.random);

        Element drawnElement = this.elementBag.drawWarmElement();
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }
}

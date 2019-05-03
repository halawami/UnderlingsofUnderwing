package elements.drawing;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import element.Element;
import element.ElementBag;
import element.ElementColor;
import element.ElementFactory;
import gui.DrawChoice;

public class DrawChoiceTests {

    Random random;
    ElementBag elementBag;

    @Before
    public void init() {
        this.random = EasyMock.mock(Random.class);
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andReturn(1);
        EasyMock.replay(this.random);

        this.elementBag = new ElementBag(new ElementFactory(), this.random);
    }

    @Test
    public void testRandom() {
        this.testDrawChoice(DrawChoice.RANDOM, ElementColor.BLUE);
    }

    @Test
    public void testWarm() {
        this.testDrawChoice(DrawChoice.WARM, ElementColor.RED);
    }

    @Test
    public void testCool() {
        this.testDrawChoice(DrawChoice.COOL, ElementColor.BLUE);
    }

    @Test
    public void testRed() {
        this.testDrawChoice(DrawChoice.RED, ElementColor.RED);
    }

    @Test
    public void testYellow() {
        this.testDrawChoice(DrawChoice.YELLOW, ElementColor.YELLOW);
    }

    @Test
    public void testBlue() {
        this.testDrawChoice(DrawChoice.BLUE, ElementColor.BLUE);
    }

    @Test
    public void testGreen() {
        this.testDrawChoice(DrawChoice.GREEN, ElementColor.GREEN);
    }

    @Test
    public void testOrange() {
        this.testDrawChoice(DrawChoice.ORANGE, ElementColor.ORANGE);
    }

    @Test
    public void testPurple() {
        this.testDrawChoice(DrawChoice.PURPLE, ElementColor.PURPLE);
    }

    @Test
    public void testBlack() {
        this.testDrawChoice(DrawChoice.BLACK, ElementColor.BLACK);
    }

    @Test
    public void testWhite() {
        this.testDrawChoice(DrawChoice.WHITE, ElementColor.WHITE);
    }

    private void testDrawChoice(DrawChoice drawChoice, ElementColor color) {
        Element element = this.elementBag.drawElement(drawChoice);

        EasyMock.verify(this.random);
        assertEquals(color, element.getColor());
    }

}

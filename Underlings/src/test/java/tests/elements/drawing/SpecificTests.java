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

public class SpecificTests {

    private ElementBag elementBag;
    private Random random;

    @Before
    public void init() {
        ElementFactory elementFactory = new ElementFactory();
        this.random = EasyMock.createMock(Random.class);
        this.elementBag = new ElementBag(elementFactory, this.random);
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(1);
        EasyMock.replay(this.random);
    }

    @Test
    public void testRed() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.RED);
        assertEquals(ElementColor.RED, drawnElement.getColor());
    }

    @Test
    public void testGreen() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.GREEN);
        assertEquals(ElementColor.GREEN, drawnElement.getColor());
    }

    @Test
    public void testBlue() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLUE);
        assertEquals(ElementColor.BLUE, drawnElement.getColor());
    }

    @Test
    public void testOrange() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.ORANGE);
        assertEquals(ElementColor.ORANGE, drawnElement.getColor());
    }

    @Test
    public void testYellow() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.YELLOW);
        assertEquals(ElementColor.YELLOW, drawnElement.getColor());
    }

    @Test
    public void testPurple() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.PURPLE);
        assertEquals(ElementColor.PURPLE, drawnElement.getColor());
    }

    @Test
    public void testBlack() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLACK);
        assertEquals(ElementColor.BLACK, drawnElement.getColor());
    }

    @Test
    public void testWhite() {
        Element drawnElement = this.elementBag.drawElementFromList(ElementColor.WHITE);
        assertEquals(ElementColor.WHITE, drawnElement.getColor());
    }

}

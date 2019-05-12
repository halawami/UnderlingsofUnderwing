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
    public void testAll() {
        assertEquals(ElementColor.RED, this.drawColor(ElementColor.RED));
        assertEquals(ElementColor.GREEN, this.drawColor(ElementColor.GREEN));
        assertEquals(ElementColor.BLUE, this.drawColor(ElementColor.BLUE));
        assertEquals(ElementColor.ORANGE, this.drawColor(ElementColor.ORANGE));
        assertEquals(ElementColor.PURPLE, this.drawColor(ElementColor.PURPLE));
        assertEquals(ElementColor.YELLOW, this.drawColor(ElementColor.YELLOW));
        assertEquals(ElementColor.BLACK, this.drawColor(ElementColor.BLACK));
        assertEquals(ElementColor.WHITE, this.drawColor(ElementColor.WHITE));
    }

    private ElementColor drawColor(ElementColor color) {
        Element drawnElement = this.elementBag.drawElementFromList(color);
        return drawnElement.getColor();
    }

}

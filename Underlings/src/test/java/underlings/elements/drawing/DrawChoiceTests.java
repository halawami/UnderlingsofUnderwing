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
import underlings.gui.DrawChoice;

public class DrawChoiceTests {

    private Random random;
    private ElementBag elementBag;

    @Before
    public void init() {
        this.random = EasyMock.mock(Random.class);
        EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andReturn(1);
        EasyMock.replay(this.random);

        this.elementBag = new ElementBag(new ElementFactory(), this.random);
    }

    @Test
    public void testAll() {
        assertEquals(ElementColor.BLUE, this.drawChoice(DrawChoice.RANDOM));
        assertEquals(ElementColor.RED, this.drawChoice(DrawChoice.WARM));
        assertEquals(ElementColor.BLUE, this.drawChoice(DrawChoice.COOL));
        assertEquals(ElementColor.RED, this.drawChoice(DrawChoice.RED));
        assertEquals(ElementColor.YELLOW, this.drawChoice(DrawChoice.YELLOW));
        assertEquals(ElementColor.BLUE, this.drawChoice(DrawChoice.BLUE));
        assertEquals(ElementColor.GREEN, this.drawChoice(DrawChoice.GREEN));
        assertEquals(ElementColor.ORANGE, this.drawChoice(DrawChoice.ORANGE));
        assertEquals(ElementColor.PURPLE, this.drawChoice(DrawChoice.PURPLE));
        assertEquals(ElementColor.BLACK, this.drawChoice(DrawChoice.BLACK));
        assertEquals(ElementColor.WHITE, this.drawChoice(DrawChoice.WHITE));
    }

    private ElementColor drawChoice(DrawChoice drawChoice) {
        Element element = this.elementBag.drawElement(drawChoice);
        this.init();
        return element.getColor();
    }

}

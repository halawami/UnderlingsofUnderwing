package underlings.effect.element;

import static org.junit.Assert.assertEquals;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.effect.domestic.CollectElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class CollectElementEffectTests extends MockTest {

    @Before
    public void init() {
        this.player = EasyMock.mock(Player.class);
        this.elementBag = EasyMock.mock(ElementBag.class);
    }

    @Test
    public void testToString() {
        this.replayAll();
        CollectElementEffect elementEffect = new CollectElementEffect();

        elementEffect.elementChoices = new ElementColor[] {ElementColor.BLACK, ElementColor.WHITE};

        assertEquals("Collect one of the following elements randomly: [ Black White ]", elementEffect.toString());

    }

    @Test
    public void testOneColor() {
        Element blueElement = new Element(ElementColor.BLUE);

        CollectElementEffect collectElementEffect = new CollectElementEffect();
        collectElementEffect.on(this.player).on(this.elementBag);
        ElementColor[] elementChoices = new ElementColor[] {ElementColor.BLUE};
        collectElementEffect.elementChoices = elementChoices;

        EasyMock.expect(this.elementBag.drawElementFromList(elementChoices)).andReturn(blueElement);
        this.player.addElement(blueElement);

        this.replayAll();

        collectElementEffect.apply();
    }

    @Test
    public void testTwoColors() {
        Element blueElement = new Element(ElementColor.BLUE);

        CollectElementEffect collectElementEffect = new CollectElementEffect();
        collectElementEffect.on(this.player).on(this.elementBag);
        ElementColor[] elementChoices = new ElementColor[] {ElementColor.BLUE, ElementColor.RED};
        collectElementEffect.elementChoices = elementChoices;

        EasyMock.expect(this.elementBag.drawElementFromList(elementChoices)).andReturn(blueElement);
        this.player.addElement(blueElement);

        this.replayAll();

        collectElementEffect.apply();
    }

}

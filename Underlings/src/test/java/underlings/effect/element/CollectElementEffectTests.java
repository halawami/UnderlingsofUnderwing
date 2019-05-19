package underlings.effect.element;

import static org.junit.Assert.assertEquals;

import java.text.MessageFormat;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.domestic.element.CollectElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class CollectElementEffectTests extends MockTest {

    @Before
    public void init() {
        this.player = this.mock(Player.class);
        this.elementBag = this.mock(ElementBag.class);
    }

    @Test
    public void testOneColor() {
        this.testColors(new ElementColor[] {ElementColor.BLUE});
    }

    @Test
    public void testTwoColors() {
        this.testColors(new ElementColor[] {ElementColor.BLUE, ElementColor.RED});
    }

    private void testColors(ElementColor[] elementChoices) {
        Element blueElement = new Element(ElementColor.BLUE);

        CollectElementEffect collectElementEffect = new CollectElementEffect();
        collectElementEffect.on(this.player).on(this.elementBag);
        collectElementEffect.elementChoices = elementChoices;

        EasyMock.expect(this.elementBag.drawElementFromList(elementChoices)).andReturn(blueElement);
        this.player.addElement(blueElement);

        this.replayAll();

        collectElementEffect.apply();
    }

    @Test
    public void testToString() {
        this.replayAll();
        CollectElementEffect effect = new CollectElementEffect();
        effect.elementChoices = new ElementColor[] {ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementChoices) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(MessageFormat.format(LocaleWrap.get("collect_element_effect"), elements), effect.toString());
    }

}

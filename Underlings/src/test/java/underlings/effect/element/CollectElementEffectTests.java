package underlings.effect.element;

import static org.junit.Assert.assertEquals;

import java.text.MessageFormat;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.domestic.CollectElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class CollectElementEffectTests {

    @Test
    public void testOneColor() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element blueElement = new Element(ElementColor.BLUE);

        CollectElementEffect collectElementEffect = new CollectElementEffect();
        collectElementEffect.on(player).on(elementBag);
        ElementColor[] elementChoices = new ElementColor[] {ElementColor.BLUE};
        collectElementEffect.elementChoices = elementChoices;

        EasyMock.expect(elementBag.drawElementFromList(elementChoices)).andReturn(blueElement);
        player.addElement(blueElement);

        EasyMock.replay(player, elementBag);

        collectElementEffect.apply();
        EasyMock.verify(player, elementBag);
    }

    @Test
    public void testTwoColors() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element blueElement = new Element(ElementColor.BLUE);

        CollectElementEffect collectElementEffect = new CollectElementEffect();
        collectElementEffect.on(player).on(elementBag);
        ElementColor[] elementChoices = new ElementColor[] {ElementColor.BLUE, ElementColor.RED};
        collectElementEffect.elementChoices = elementChoices;

        EasyMock.expect(elementBag.drawElementFromList(elementChoices)).andReturn(blueElement);
        player.addElement(blueElement);

        EasyMock.replay(player, elementBag);

        collectElementEffect.apply();
        EasyMock.verify(player, elementBag);
    }

    @Test
    public void testToString() {
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

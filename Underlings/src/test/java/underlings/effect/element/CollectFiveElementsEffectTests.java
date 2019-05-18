package underlings.effect.element;

import static org.junit.Assert.assertEquals;
import static underlings.element.ElementColor.BLACK;
import static underlings.element.ElementColor.BLUE;
import static underlings.element.ElementColor.GREEN;
import static underlings.element.ElementColor.RED;
import static underlings.element.ElementColor.WHITE;

import java.text.MessageFormat;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.domestic.CollectFiveElementsEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class CollectFiveElementsEffectTests {

    @Test
    public void testEffect() {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player player = EasyMock.mock(Player.class);
        CollectFiveElementsEffect testedEffect = new CollectFiveElementsEffect();
        ElementColor[] colors = new ElementColor[] {RED, BLUE, GREEN, BLACK, WHITE};
        testedEffect.elementColors = colors;
        List<Element> mockElements = TestUtils.mockListOf(Element.class).withLength(5);

        for (int i = 0; i < 5; i++) {
            EasyMock.expect(elementBag.drawElementFromList(colors[i])).andReturn(mockElements.get(i));
            player.addElement(mockElements.get(i));
        }

        EasyMock.replay(elementBag, player);
        mockElements.forEach(EasyMock::replay);

        testedEffect.on(elementBag).on(player).apply();

        EasyMock.verify(elementBag, player);
        mockElements.forEach(EasyMock::verify);
    }

    @Test
    public void testToString() {
        CollectFiveElementsEffect effect = new CollectFiveElementsEffect();
        effect.elementColors = new ElementColor[] {ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementColors) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(MessageFormat.format(LocaleWrap.get("collect_five_element_effect"), elements), effect.toString());
    }

}

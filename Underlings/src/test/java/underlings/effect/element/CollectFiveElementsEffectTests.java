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

import underlings.MockTest;
import underlings.card.effect.domestic.element.CollectFiveElementsEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class CollectFiveElementsEffectTests extends MockTest {

    @Test
    public void testEffect() {
        ElementBag elementBag = this.mock(ElementBag.class);
        Player player = this.mock(Player.class);
        CollectFiveElementsEffect effect = new CollectFiveElementsEffect();
        ElementColor[] colors = new ElementColor[] {RED, BLUE, GREEN, BLACK, WHITE};
        effect.elementColors = colors;
        List<Element> mockElements = this.mockListOf(Element.class).withLengthOf(5);

        for (int i = 0; i < 5; i++) {
            EasyMock.expect(elementBag.drawElementFromList(colors[i])).andReturn(mockElements.get(i));
            player.addElement(mockElements.get(i));
        }

        this.replayAll();

        effect.on(elementBag).on(player).apply();
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

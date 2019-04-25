package tests.effect;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.CollectElementEffect;
import underlings.card.effect.domestic.CollectPrimaryElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class ElementTests {

    @Test
    public void testGainPrimaryElement() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element element = new Element(ElementColor.BLUE);

        Effect gainPrimaryElement = new CollectPrimaryElementEffect();

        EasyMock.expect(elementBag.drawRandomPrimaryElement()).andReturn(element);
        player.addElement(element);

        EasyMock.replay(player, elementBag);

        gainPrimaryElement.apply();
        EasyMock.verify(player, elementBag);
    }

    @Test
    public void testCollectElement() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element blueElement = new Element(ElementColor.BLUE);

        CollectElementEffect collectElementEffect = new CollectElementEffect();
        ElementColor[] elementChoices = new ElementColor[]{ElementColor.BLUE};
        collectElementEffect.elementChoices = elementChoices;

        EasyMock.expect(elementBag.drawElementFromList(elementChoices)).andReturn(blueElement);
        player.addElement(blueElement);

        EasyMock.replay(player, elementBag);

        collectElementEffect.apply();
        EasyMock.verify(player, elementBag);
    }

}

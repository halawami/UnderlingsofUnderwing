package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.domestic.CollectUpToTwoElementsFromAnyEggInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.player.Player;

public class CollectUpToTwoElementsFromAnyEggInPlayEffectTests {


    @Test
    public void testNullElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = NullElement.getInstance();
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();

        currentPlayer.addElement(element);
        elementSpace.destroyOneElementOfColor(element.getColor());

        EasyMock.replay(currentPlayer, elementSpace);

        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace);
    }


    @Test
    public void testNormalElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = EasyMock.mock(Element.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();

        currentPlayer.addElement(element);
        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        EasyMock.replay(currentPlayer, elementSpace, element);

        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace, element);
    }
}

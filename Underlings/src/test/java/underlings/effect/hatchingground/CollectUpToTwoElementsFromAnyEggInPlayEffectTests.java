package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.CollectUpToTwoElementsFromAnyEggInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class CollectUpToTwoElementsFromAnyEggInPlayEffectTests {

    @Test
    public void testNoElementsPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[]{ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace).times(2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(NullElement.getInstance()).times(2);

        currentPlayer.addElement(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(NullElement.getInstance().getColor());
        currentPlayer.addElement(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(NullElement.getInstance().getColor());

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace);
    }

    @Test
    public void testFirstElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = EasyMock.mock(Element.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[]{ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace).times(2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(element);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(NullElement.getInstance());

        currentPlayer.addElement(element);
        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);
        currentPlayer.addElement(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(NullElement.getInstance().getColor());

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, element);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace, element);
    }

    @Test
    public void testSecondElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = EasyMock.mock(Element.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[]{ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace).times(2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(NullElement.getInstance());
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(element);

        currentPlayer.addElement(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(NullElement.getInstance().getColor());
        currentPlayer.addElement(element);
        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, element);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace, element);
    }

    @Test
    public void testTwoElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element firstElement = EasyMock.mock(Element.class);
        Element secondElement = EasyMock.mock(Element.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[]{ElementColor.BLUE, ElementColor.RED};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace).times(2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(firstElement);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(secondElement);

        currentPlayer.addElement(firstElement);
        EasyMock.expect(firstElement.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);
        currentPlayer.addElement(secondElement);
        EasyMock.expect(secondElement.getColor()).andReturn(ElementColor.RED);
        elementSpace.destroyOneElementOfColor(ElementColor.RED);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, firstElement, secondElement);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace, firstElement, secondElement);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

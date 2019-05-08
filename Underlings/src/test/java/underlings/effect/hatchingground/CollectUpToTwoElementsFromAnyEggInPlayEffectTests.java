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

    public void testWithElements(Element firstElement, Element secondElement,
            ElementColor... testedEffectElementChoices) {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();
        testedEffect.elementChoices = testedEffectElementChoices;
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(
                gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffectElementChoices))
                .andReturn(elementSpace).times(2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(firstElement);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(secondElement);

        currentPlayer.addElement(firstElement);
        elementSpace.destroyOneElementOfColor(firstElement.getColor());
        currentPlayer.addElement(secondElement);
        elementSpace.destroyOneElementOfColor(secondElement.getColor());

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace);
        this.verifyElements(firstElement, secondElement);

    }

    @Test
    public void testNoElementsPicked() {
        this.testWithElements(NullElement.getInstance(), NullElement.getInstance(), ElementColor.BLUE);
    }

    @Test
    public void testFirstElementPicked() {
        Element firstElement = EasyMock.mock(Element.class);
        EasyMock.expect(firstElement.getColor()).andStubReturn(ElementColor.BLUE);
        EasyMock.replay(firstElement);

        this.testWithElements(firstElement, NullElement.getInstance(), ElementColor.BLUE);
    }

    @Test
    public void testSecondElementPicked() {
        Element secondElement = EasyMock.mock(Element.class);
        EasyMock.expect(secondElement.getColor()).andReturn(ElementColor.BLUE).anyTimes();
        EasyMock.replay(secondElement);

        this.testWithElements(NullElement.getInstance(), secondElement, ElementColor.BLUE);
    }

    @Test
    public void testTwoElementPicked() {
        Element firstElement = EasyMock.mock(Element.class);
        EasyMock.expect(firstElement.getColor()).andReturn(ElementColor.BLUE).anyTimes();
        EasyMock.replay(firstElement);

        Element secondElement = EasyMock.mock(Element.class);
        EasyMock.expect(secondElement.getColor()).andReturn(ElementColor.RED).anyTimes();
        EasyMock.replay(secondElement);

        this.testWithElements(firstElement, secondElement, ElementColor.BLUE, ElementColor.RED);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    private void verifyElements(Element firstElement, Element secondElement) {
        if (firstElement != NullElement.getInstance()) {
            EasyMock.verify(firstElement);
        }
        if (secondElement != NullElement.getInstance()) {
            EasyMock.verify(secondElement);
        }
    }
}

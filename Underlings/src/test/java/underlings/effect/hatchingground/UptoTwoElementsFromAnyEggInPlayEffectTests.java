package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.CollectUpToTwoElementsFromAnyEggInPlayEffect;
import underlings.card.effect.domestic.DestroyUpToTwoElementsOnAnyEggInPlayEffect;
import underlings.card.effect.domestic.UptoTwoElementsFromAnyEggInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class UptoTwoElementsFromAnyEggInPlayEffectTests {

    @Test
    public void testApplyOnSelectedElement() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace1 = EasyMock.mock(ElementSpace.class);
        ElementSpace elementSpace2 = EasyMock.mock(ElementSpace.class);
        Element element1 = EasyMock.mock(Element.class);
        Element element2 = EasyMock.mock(Element.class);
        UptoTwoElementsFromAnyEggInPlayEffect testedEffect = EasyMock
                .partialMockBuilder(UptoTwoElementsFromAnyEggInPlayEffect.class)
                .addMockedMethod("applyOnSelectedElement").createMock();
        testedEffect.elementChoices = new ElementColor[]{ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(
                gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace1);
        EasyMock.expect(
                gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace1))
                .andReturn(element1);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace2))
                .andReturn(element2);

        testedEffect.applyOnSelectedElement(element1, elementSpace1, currentPlayer);
        testedEffect.applyOnSelectedElement(element2, elementSpace2, currentPlayer);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace1, elementSpace2, element1, element2,
                testedEffect);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace1, elementSpace2, element1, element2,
                testedEffect);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    @Test
    public void testDestroyNullElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = NullElement.getInstance();
        DestroyUpToTwoElementsOnAnyEggInPlayEffect testedEffect = new DestroyUpToTwoElementsOnAnyEggInPlayEffect();

        elementSpace.destroyOneElementOfColor(element.getColor());

        EasyMock.replay(currentPlayer, elementSpace);

        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace);
    }

    @Test
    public void testDestroyNormalElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = EasyMock.mock(Element.class);
        DestroyUpToTwoElementsOnAnyEggInPlayEffect testedEffect = new DestroyUpToTwoElementsOnAnyEggInPlayEffect();

        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        EasyMock.replay(currentPlayer, elementSpace, element);

        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace, element);
    }

    @Test
    public void testCollectNullElementPicked() {
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
    public void testCollectNormalElementPicked() {
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

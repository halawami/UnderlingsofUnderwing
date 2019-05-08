package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.UptoTwoElementsFromAnyEggInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
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
}

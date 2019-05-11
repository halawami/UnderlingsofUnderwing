package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.AddElementToAllSpacesInPlayEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.utilities.EggHatchingLogic;

public class AddElementToAllSpacesInPlayEffectTests {

    @Test
    public void testApplyOnOneCardInPlay() {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Gui gui = EasyMock.mock(Gui.class);
        List<Card> mockedCards = getMockedCards(1);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        AddElementToAllSpacesInPlayEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementToAllSpacesInPlayEffect.class)
                        .addMockedMethod("addElementsToCard").createMock();
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE};

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.addElementsToCard(ElementColor.BLUE, mockedCard, elementSpaceLogic, elementBag);
        }
        EasyMock.replay(elementBag, centerCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.replay(eggHatchingLogic);

        testedEffect.apply();

        EasyMock.verify(elementBag, centerCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.verify(eggHatchingLogic);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}

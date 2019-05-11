package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.AddElementToAllSpacesInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class AddElementToAllSpacesInPlayEffectTests {

    @Test
    public void testApplyOnNoCardsInPlay() {
        testApplyOnCardsInPlay(0);
    }

    @Test
    public void testApplyOnOneCardInPlay() {
        testApplyOnCardsInPlay(1);
    }

    @Test
    public void testApplyOnTwoCardsInPlay() {
        testApplyOnCardsInPlay(2);
    }

    public void testApplyOnCardsInPlay(int numberOfCards) {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Gui gui = EasyMock.mock(Gui.class);
        Player player = new Player(0, null, 0);
        player.elementSpaceLogic = elementSpaceLogic;
        List<Card> mockedCards = getMockedCards(numberOfCards);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        AddElementToAllSpacesInPlayEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementToAllSpacesInPlayEffect.class)
                        .addMockedMethod("addElementsToCard").createMock();
        testedEffect.on(elementBag).on(centerCard).on(hatchingGround).on(gui).on(eggHatchingLogic).on(player);
        testedEffect.elementColor = ElementColor.BLUE;

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

    @Test
    public void testAddElementsToCardNoPlayableSpaces() {
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Card mockedCard = EasyMock.mock(Card.class);
        ElementColor blue = ElementColor.BLUE;
        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);

        AddElementToAllSpacesInPlayEffect testedEffect = new AddElementToAllSpacesInPlayEffect();
        testedEffect.addElementsToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag);
    }

    @Test
    public void testAddElementToCardOnePlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element stubElement = EasyMock.niceMock(Element.class);
        ElementSpace mockedPlayableSpace = EasyMock.mock(ElementSpace.class);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue))
                .andReturn(Arrays.asList(mockedPlayableSpace));
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpace.addElements(stubElement);

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag, mockedPlayableSpace);

        AddElementToAllSpacesInPlayEffect testedEffect = new AddElementToAllSpacesInPlayEffect();
        testedEffect.addElementsToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag, mockedPlayableSpace);
    }


    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}

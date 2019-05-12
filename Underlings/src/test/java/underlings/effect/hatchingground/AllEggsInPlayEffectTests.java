package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.AddElementToAllEggsInPlayEffect;
import underlings.card.effect.wild.AllEggsInPlayEffect;
import underlings.card.effect.wild.DestroyAllElementsOnAllEggsInPlay;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class AllEggsInPlayEffectTests {

    @Test
    public void testApplyOnNoEggInPlay() {
        testApplyOnCardInPlay(0);
    }

    @Test
    public void testApplyOnOneEggInPlay() {
        testApplyOnCardInPlay(1);
    }

    @Test
    public void testApplyOnEggsInPlay() {
        testApplyOnCardInPlay(2);
    }

    public void testApplyOnCardInPlay(int numberOfCards) {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Gui gui = EasyMock.mock(Gui.class);
        Player player = new Player(0, null, 0);
        player.elementSpaceLogic = elementSpaceLogic;
        List<Card> mockedCards = getMockedCards(numberOfCards);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        AllEggsInPlayEffect testedEffect =
                EasyMock.partialMockBuilder(AllEggsInPlayEffect.class)
                        .addMockedMethod("applyOnCardInPlay").createMock();
        testedEffect.on(elementBag).on(centerCard).on(hatchingGround).on(gui).on(eggHatchingLogic).on(player);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.applyOnCardInPlay(mockedCard, elementSpaceLogic, elementBag);
        }
        EasyMock.replay(elementBag, centerCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.replay(eggHatchingLogic);

        testedEffect.apply();

        EasyMock.verify(elementBag, centerCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.verify(eggHatchingLogic);
    }

    @Test
    public void testAddElementsToCardNoPlayableSpaces() {
        testAddElementsToCardWithPlayableSpace(0);
    }

    @Test
    public void testAddElementsToCardOnePlayableSpace() {
        testAddElementsToCardWithPlayableSpace(1);
    }

    @Test
    public void testAddElementsToCardEightPlayableSpace() {
        testAddElementsToCardWithPlayableSpace(8);
    }


    public void testAddElementsToCardWithPlayableSpace(int numberOfPlayableSpaces) {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element stubElement = EasyMock.niceMock(Element.class);
        List<ElementSpace> mockedPlayableSpaces = getMockSpaces(numberOfPlayableSpaces);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(mockedPlayableSpaces);

        for (ElementSpace mockSpace : mockedPlayableSpaces) {
            EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
            mockSpace.addElements(stubElement);
        }

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);
        mockedPlayableSpaces.forEach(EasyMock::replay);

        AddElementToAllEggsInPlayEffect testedEffect = new AddElementToAllEggsInPlayEffect();
        testedEffect.elementColor = ElementColor.BLUE;
        testedEffect.applyOnCardInPlay(mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag);
        mockedPlayableSpaces.forEach(EasyMock::verify);
    }

    @Test
    public void testDestroyAllElementsOnUnclaimedCard() {
        Card cardInPlay = new Card();
        List<ElementSpace> mockSpaces = getMockSpaces(8);
        cardInPlay.elementSpaces = mockSpaces.toArray(new ElementSpace[8]);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        EasyMock.replay(elementSpaceLogic, elementBag);
        mockSpaces.forEach(EasyMock::replay);

        DestroyAllElementsOnAllEggsInPlay testedEffect = new DestroyAllElementsOnAllEggsInPlay();
        testedEffect.applyOnCardInPlay(cardInPlay, elementSpaceLogic, elementBag);

        EasyMock.verify(elementSpaceLogic, elementBag);
        mockSpaces.forEach(EasyMock::verify);
    }

    @Test
    public void testDestroyAllElementsOnClaimedCard() {
        Card cardInPlay = new Card();
        cardInPlay.handler = EasyMock.niceMock(Handler.class);
        List<ElementSpace> mockSpaces = getMockSpaces(8);
        cardInPlay.elementSpaces = mockSpaces.toArray(new ElementSpace[8]);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        mockSpaces.forEach(ElementSpace::destroyAllElements);

        EasyMock.replay(elementSpaceLogic, elementBag);
        mockSpaces.forEach(EasyMock::replay);

        DestroyAllElementsOnAllEggsInPlay testedEffect = new DestroyAllElementsOnAllEggsInPlay();
        testedEffect.applyOnCardInPlay(cardInPlay, elementSpaceLogic, elementBag);

        EasyMock.verify(elementSpaceLogic, elementBag);
        mockSpaces.forEach(EasyMock::verify);
    }

    private List<ElementSpace> getMockSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(EasyMock.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}

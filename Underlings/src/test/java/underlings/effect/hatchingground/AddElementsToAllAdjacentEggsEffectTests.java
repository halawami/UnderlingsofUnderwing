package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.AddElementsToAllAdjacentEggsEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class AddElementsToAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        AddElementsToAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsToAllAdjacentEggsEffect.class)
                        .addMockedMethod("addElementToCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE};

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.addElementToCard(ElementColor.BLUE, mockedCard, elementSpaceLogic, elementBag);
        }

        EasyMock.replay(centerCard, hatchingGround, elementBag, elementSpaceLogic, testedEffect);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, elementBag, elementSpaceLogic, testedEffect);
    }

    @Test
    public void testApplyTwoDifferentElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        AddElementsToAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsToAllAdjacentEggsEffect.class)
                        .addMockedMethod("addElementToCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.RED};

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.addElementToCard(ElementColor.BLUE, mockedCard, elementSpaceLogic, elementBag);
        }
        for (Card mockedCard : mockedCards) {
            testedEffect.addElementToCard(ElementColor.RED, mockedCard, elementSpaceLogic, elementBag);
        }

        EasyMock.replay(centerCard, hatchingGround, elementBag, elementSpaceLogic, testedEffect);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, elementBag, elementSpaceLogic, testedEffect);
    }

    @Test
    public void testApplyTwoSameElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        AddElementsToAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsToAllAdjacentEggsEffect.class)
                        .addMockedMethod("addElementToCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.BLUE};

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.addElementToCard(ElementColor.BLUE, mockedCard, elementSpaceLogic, elementBag);
        }
        for (Card mockedCard : mockedCards) {
            testedEffect.addElementToCard(ElementColor.BLUE, mockedCard, elementSpaceLogic, elementBag);
        }

        EasyMock.replay(centerCard, hatchingGround, elementBag, elementSpaceLogic, testedEffect);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, elementBag, elementSpaceLogic, testedEffect);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    @Test
    public void testAddElementToCardNoPlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        AddElementsToAllAdjacentEggsEffect testedEffect = new AddElementsToAllAdjacentEggsEffect();

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);

        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

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

        AddElementsToAllAdjacentEggsEffect testedEffect = new AddElementsToAllAdjacentEggsEffect();
        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag, mockedPlayableSpace);
    }

    @Test
    public void testAddElementToCardEightPlayableSpaces() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element stubElement = EasyMock.niceMock(Element.class);
        List<ElementSpace> mockedPlayableSpaces = getMockedPlayableSpaces(8);


        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpaces.get(0).addElements(stubElement);

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);
        for (ElementSpace mockedPlayableSpace : mockedPlayableSpaces) {
            EasyMock.replay(mockedPlayableSpace);
        }

        AddElementsToAllAdjacentEggsEffect testedEffect = new AddElementsToAllAdjacentEggsEffect();
        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag);
        for (ElementSpace mockedPlayableSpace : mockedPlayableSpaces) {
            EasyMock.verify(mockedPlayableSpace);
        }
    }

    private List<ElementSpace> getMockedPlayableSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(EasyMock.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

    @Test
    public void testToString() {
        AddElementsToAllAdjacentEggsEffect testedEffect = new AddElementsToAllAdjacentEggsEffect();
        assertEquals("Add elements to adjacent cards", testedEffect.toString());
    }

}

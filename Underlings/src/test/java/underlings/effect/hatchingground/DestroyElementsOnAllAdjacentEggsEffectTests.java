package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.destroy.AllElementsOnAllAdjacentEggsEffect;
import underlings.card.effect.wild.adjacenteggs.destroy.ElementsOnAllAdjacentEggsEffect;
import underlings.card.effect.wild.adjacenteggs.destroy.OneElementOnAllAdjacentEggsEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class DestroyElementsOnAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        ElementsOnAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(ElementsOnAllAdjacentEggsEffect.class)
                        .addMockedMethod("destroyElementsOfColorOnCard").createMock();
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE};

        testedEffect.destroyElementsOfColorOnCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic);

        EasyMock.replay(testedEffect, elementSpaceLogic, adjacentCard);

        testedEffect.applyOnAdjacentEgg(adjacentCard, null, elementSpaceLogic);

        EasyMock.verify(testedEffect, elementSpaceLogic, adjacentCard);
    }

    @Test
    public void testApplyTwoElementColor() {
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        ElementsOnAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(ElementsOnAllAdjacentEggsEffect.class)
                        .addMockedMethod("destroyElementsOfColorOnCard").createMock();
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE, ElementColor.RED};

        testedEffect.destroyElementsOfColorOnCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic);
        testedEffect.destroyElementsOfColorOnCard(ElementColor.RED, adjacentCard, elementSpaceLogic);

        EasyMock.replay(testedEffect, elementSpaceLogic, adjacentCard);

        testedEffect.applyOnAdjacentEgg(adjacentCard, null, elementSpaceLogic);

        EasyMock.verify(testedEffect, elementSpaceLogic, adjacentCard);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    @Test
    public void testDestroyElementOnCardNoDestroyableElements() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);

        ElementsOnAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(ElementsOnAllAdjacentEggsEffect.class)
                        .addMockedMethod("destroyElementsOfColorOnSpace").createMock();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        EasyMock.replay(mockedCard, elementSpaceLogic);

        testedEffect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic);
    }

    @Test
    public void testDestroyElementOnCardOneDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementSpace mockedDestroyableSpace = EasyMock.mock(ElementSpace.class);

        ElementsOnAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(ElementsOnAllAdjacentEggsEffect.class)
                        .addMockedMethod("destroyElementsOfColorOnSpace").createMock();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue))
                .andReturn(Arrays.asList(mockedDestroyableSpace));
        testedEffect.destroyElementsOfColorOnSpace(mockedDestroyableSpace, blue);

        EasyMock.replay(mockedCard, elementSpaceLogic, mockedDestroyableSpace);

        testedEffect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic, mockedDestroyableSpace);
    }

    @Test
    public void testDestroyElementOnCardEightDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        List<ElementSpace> mockedDestroyableSpaces = getMockedDestroyableSpaces(8);

        ElementsOnAllAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(ElementsOnAllAdjacentEggsEffect.class)
                        .addMockedMethod("destroyElementsOfColorOnSpace").createMock();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(mockedDestroyableSpaces);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            testedEffect.destroyElementsOfColorOnSpace(destroyableSpace, blue);
        }

        EasyMock.replay(mockedCard, elementSpaceLogic);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            EasyMock.replay(destroyableSpace);
        }

        testedEffect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            EasyMock.verify(destroyableSpace);
        }
    }

    @Test
    public void testDestroyAllElementsOnAllAdjacentEggsEffect() {
        ElementColor blue = ElementColor.BLUE;
        ElementSpace mockElementSpace = EasyMock.mock(ElementSpace.class);

        AllElementsOnAllAdjacentEggsEffect testedEffect = new AllElementsOnAllAdjacentEggsEffect();

        mockElementSpace.destroyAllElementsOfColor(blue);

        EasyMock.replay(mockElementSpace);

        testedEffect.destroyElementsOfColorOnSpace(mockElementSpace, blue);

        EasyMock.verify(mockElementSpace);
    }

    @Test
    public void testDestroyOneElementOnAllAdjacentEggsEffect() {
        ElementColor blue = ElementColor.BLUE;
        ElementSpace mockElementSpace = EasyMock.mock(ElementSpace.class);

        OneElementOnAllAdjacentEggsEffect testedEffect = new OneElementOnAllAdjacentEggsEffect();

        mockElementSpace.destroyOneElementOfColor(blue);

        EasyMock.replay(mockElementSpace);

        testedEffect.destroyElementsOfColorOnSpace(mockElementSpace, blue);

        EasyMock.verify(mockElementSpace);
    }

    private List<ElementSpace> getMockedDestroyableSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(EasyMock.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

}

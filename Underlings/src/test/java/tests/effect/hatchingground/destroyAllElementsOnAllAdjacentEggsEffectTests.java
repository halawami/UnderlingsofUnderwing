package tests.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.wild.destroyAllElementsOnAllAdjacentEggsEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class destroyAllElementsOnAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        destroyAllElementsOnAllAdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(destroyAllElementsOnAllAdjacentEggsEffect.class)
                .addMockedMethod("destroyAllElementsOfColorOnCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE};

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.destroyAllElementsOfColorOnCard(ElementColor.BLUE, mockedCard, elementSpaceLogic);
        }

        EasyMock.replay(centerCard, hatchingGround, testedEffect, elementSpaceLogic);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, testedEffect, elementSpaceLogic);
    }

    @Test
    public void testApplyTwoElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        destroyAllElementsOnAllAdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(destroyAllElementsOnAllAdjacentEggsEffect.class)
                .addMockedMethod("destroyAllElementsOfColorOnCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE, ElementColor.RED};

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.destroyAllElementsOfColorOnCard(ElementColor.BLUE, mockedCard, elementSpaceLogic);
        }
        for (Card mockedCard : mockedCards) {
            testedEffect.destroyAllElementsOfColorOnCard(ElementColor.RED, mockedCard, elementSpaceLogic);
        }


        EasyMock.replay(centerCard, hatchingGround, testedEffect);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, testedEffect);
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

        destroyAllElementsOnAllAdjacentEggsEffect testedEffect = new destroyAllElementsOnAllAdjacentEggsEffect();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        EasyMock.replay(mockedCard, elementSpaceLogic);

        testedEffect.destroyAllElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic);
    }

    @Test
    public void testDestroyElementOnCardOneDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementSpace mockedDestroyableSpace = EasyMock.mock(ElementSpace.class);

        destroyAllElementsOnAllAdjacentEggsEffect testedEffect = new destroyAllElementsOnAllAdjacentEggsEffect();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(Arrays.asList(mockedDestroyableSpace));
        mockedDestroyableSpace.destroyAllElementsOfColor(blue);

        EasyMock.replay(mockedCard, elementSpaceLogic, mockedDestroyableSpace);

        testedEffect.destroyAllElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic, mockedDestroyableSpace);
    }

    @Test
    public void testDestroyElementOnCardEightDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        List<ElementSpace> mockedDestroyableSpaces = getMockedDestroyableSpaces(8);

        destroyAllElementsOnAllAdjacentEggsEffect testedEffect = new destroyAllElementsOnAllAdjacentEggsEffect();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(mockedDestroyableSpaces);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            destroyableSpace.destroyAllElementsOfColor(blue);
        }

        EasyMock.replay(mockedCard, elementSpaceLogic);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            EasyMock.replay(destroyableSpace);
        }

        testedEffect.destroyAllElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            EasyMock.verify(destroyableSpace);
        }
    }

    private List<ElementSpace> getMockedDestroyableSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(EasyMock.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

}

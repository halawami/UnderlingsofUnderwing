package tests.effect;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.wild.AddElementsToAllAdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddElementsToAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        AddElementsToAllAdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AddElementsToAllAdjacentEggsEffect.class)
                .addMockedMethod("addElementToCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE};

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
    public void testApplyTwoElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        AddElementsToAllAdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AddElementsToAllAdjacentEggsEffect.class)
                .addMockedMethod("addElementToCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(elementSpaceLogic);
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE, ElementColor.RED};

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

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    @Test
    public void testAddElementToCardNotAddable() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        AddElementsToAllAdjacentEggsEffect testedEffect = new AddElementsToAllAdjacentEggsEffect();
        testedEffect.on(mockedCard).on(elementSpaceLogic).on(elementBag);
        testedEffect.elementColors = new ElementColor[]{blue};


        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(Arrays.asList(blue), mockedCard)).andReturn(Collections.emptyList());
        EasyMock.replay(blue, mockedCard, elementSpaceLogic, elementBag);

        testedEffect.apply();

        EasyMock.verify();
    }

}

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
import java.util.List;

public class AddElementsToAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        Card centerCard = EasyMock.niceMock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.niceMock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.niceMock(ElementSpaceLogic.class);
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

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}

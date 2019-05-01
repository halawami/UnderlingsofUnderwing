package tests.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.wild.destroyAllElementsOnAllAdjacentEggsEffect;
import underlings.element.ElementColor;
import underlings.game.HatchingGround;

import java.util.ArrayList;
import java.util.List;

public class destroyAllElementsOnAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        destroyAllElementsOnAllAdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(destroyAllElementsOnAllAdjacentEggsEffect.class)
                .addMockedMethod("destroyAllElementsOfColorOnCard").createMock();
        testedEffect.on(centerCard).on(hatchingGround);
        testedEffect.elementColors = new ElementColor[]{ElementColor.BLUE};

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.destroyAllElementsOfColorOnCard(ElementColor.BLUE, mockedCard);
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
}

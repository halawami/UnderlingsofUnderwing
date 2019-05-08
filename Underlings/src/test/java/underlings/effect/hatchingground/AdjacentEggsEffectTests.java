package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class AdjacentEggsEffectTests {

    @Test
    public void testApplyOnMultipleAdjacentCards() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        AdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AdjacentEggsEffect.class)
                .addMockedMethod("applyOnAdjacentEgg").createMock();

        testedEffect.on(centerCard).on(hatchingGround).on(elementSpaceLogic).on(elementBag);

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);

        for (Card mockedCard : mockedCards) {
            testedEffect.applyOnAdjacentEgg(mockedCard, elementBag, elementSpaceLogic);
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

package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.HatchAllUnclaimedAdjacentEggsEffect;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test
    public void testApplyNoHatchableEggs() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        HatchAllUnclaimedAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(HatchAllUnclaimedAdjacentEggsEffect.class)
                        .addMockedMethod("getHatchableEggs").createMock();
        testedEffect.on(centerCard).on(hatchingGround).on(elementSpaceLogic);

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        EasyMock.expect(testedEffect.getHatchableEggs(mockedCards)).andReturn(Collections.emptyList());

        EasyMock.replay(centerCard, hatchingGround, testedEffect, elementSpaceLogic);
        mockedCards.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, testedEffect, elementSpaceLogic);
        mockedCards.forEach(EasyMock::verify);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}

package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffect;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test
    public void testUnhatchableAdjacentEgg() {
        Card adjacentEgg = EasyMock.mock(Card.class);
        HatchAllUnclaimedAdjacentEggsEffect testedEffect = EasyMock
                .partialMockBuilder(HatchAllUnclaimedAdjacentEggsEffect.class)
                .addMockedMethod("isHatchableEgg")
                .addMockedMethod("hatchEgg")
                .createMock();

        EasyMock.expect(testedEffect.isHatchableEgg(adjacentEgg)).andReturn(false);

        EasyMock.replay(adjacentEgg, testedEffect);

        testedEffect.applyOnAdjacentEgg(adjacentEgg, null, null);

        EasyMock.verify(adjacentEgg, testedEffect);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Family;
import underlings.card.effect.wild.HatchAllUnclaimedAdjacentEggsEffect;
import underlings.game.HatchingGround;
import underlings.handler.Handler;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test
    public void testHatchOneAdjacentUnclaimedEgg() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(2);
        Handler handler = EasyMock.mock(Handler.class);
        mockedCards.get(1).handler = handler;
        mockedCards.get(1).family = Family.MONOCHROMATIC;
        mockedCards.get(0).family = Family.TRIADIC;
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);

        EasyMock.replay(centerCard, hatchingGround, handler);

        HatchAllUnclaimedAdjacentEggsEffect hatchAllUnclaimedAdjacentEggsEffect =
                new HatchAllUnclaimedAdjacentEggsEffect();
        hatchAllUnclaimedAdjacentEggsEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, handler);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

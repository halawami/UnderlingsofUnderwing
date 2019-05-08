package underlings.effect.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedAdjacentEggsEffect;
import underlings.handler.Handler;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test
    public void testClaimedAdjacentEgg() {
        Card adjacentEgg = new Card();
        adjacentEgg.handler = EasyMock.niceMock(Handler.class);
        HatchAllUnclaimedAdjacentEggsEffect testedEffect =
                EasyMock.partialMockBuilder(HatchAllUnclaimedAdjacentEggsEffect.class).addMockedMethod("hatchEgg")
                        .createMock();

        EasyMock.replay(testedEffect);

        testedEffect.applyOnAdjacentEgg(adjacentEgg, null, null);

        EasyMock.verify(testedEffect);
    }

}

package underlings.card.effect;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.ElementSpaceUtilities;

public class AdjacentEggsEffectTests extends MockTest {

    @Test
    public void testApplyOnMultipleAdjacentCards() {
        Card centerCard = this.mock(Card.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        ElementBag elementBag = this.mock(ElementBag.class);
        Player player = this.mock(Player.class);
        player.elementSpaceLogic = this.mock(ElementSpaceUtilities.class);
        Gui gui = this.mock(Gui.class);
        EggHatchingUtilities eggHatchingLogic = this.mock(EggHatchingUtilities.class);
        AdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AdjacentEggsEffect.class)
                .addMockedMethod("applyOnAdjacentEgg", Card.class, ElementSpaceUtilities.class, ElementBag.class)
                .createNiceMock();
        this.addMock(testedEffect);

        List<Card> mockedCards = this.mockListOf(Card.class).withLengthOf(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.applyOnAdjacentEgg(mockedCard, null,
                    null, hatchingGround);
        }

        this.replayAll();

        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(gui).on(eggHatchingLogic).on(player).apply();
    }

}

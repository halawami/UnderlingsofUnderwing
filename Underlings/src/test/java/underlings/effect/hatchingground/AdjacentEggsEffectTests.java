package underlings.effect.hatchingground;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class AdjacentEggsEffectTests extends MockTest {

    @Test
    public void testApplyOnMultipleAdjacentCards() {
        Card centerCard = this.mock(Card.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        ElementBag elementBag = this.mock(ElementBag.class);
        Player player = this.mock(Player.class);
        player.elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        Gui gui = this.mock(Gui.class);
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);
        AdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AdjacentEggsEffect.class)
                .addMockedMethod("applyOnAdjacentEgg", Card.class, ElementSpaceLogic.class, ElementBag.class)
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

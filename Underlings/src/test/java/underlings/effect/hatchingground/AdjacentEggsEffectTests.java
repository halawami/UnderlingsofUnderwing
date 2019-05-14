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
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class AdjacentEggsEffectTests {

    @Test
    public void testApplyOnMultipleAdjacentCards() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player player = EasyMock.mock(Player.class);
        player.elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        AdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AdjacentEggsEffect.class)
                .addMockedMethod("applyOnAdjacentEgg").createMock();

        Gui gui = EasyMock.mock(Gui.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(gui).on(eggHatchingLogic).on(player);

        List<Card> mockedCards = getMockedCards(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.applyOnAdjacentEgg(mockedCard, elementBag, player.elementSpaceLogic,
                    eggHatchingLogic,  null, null);
        }

        EasyMock.replay(centerCard, hatchingGround, elementBag, player.elementSpaceLogic, testedEffect, gui);
        EasyMock.replay(eggHatchingLogic, player);

        testedEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, elementBag, player.elementSpaceLogic, testedEffect, gui);
        EasyMock.verify(eggHatchingLogic, player);

    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}

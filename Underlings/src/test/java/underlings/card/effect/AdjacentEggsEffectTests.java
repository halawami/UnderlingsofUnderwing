package underlings.card.effect;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.Deck;
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
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        EggHatchingUtilities eggHatchingLogic = this.mock(EggHatchingUtilities.class);
        Deck deck = this.mock(Deck.class);
        AdjacentEggsEffect testedEffect = EasyMock.partialMockBuilder(AdjacentEggsEffect.class)
                .addMockedMethod("applyOnAdjacentEgg", Card.class, ElementSpaceUtilities.class, ElementBag.class)
                .addMockedMethod("applyOnAdjacentEgg", Card.class, EggHatchingUtilities.class)
                .addMockedMethod("applyOnAdjacentEgg", Card.class, HandlerMovementLogic.class)
                .addMockedMethod("applyOnAdjacentEgg", Card.class, Deck.class, HandlerMovementLogic.class,
                        HatchingGround.class)
                .createMock();
        this.addMock(testedEffect);

        List<Card> mockedCards = this.mockListOf(Card.class).withLengthOf(2);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            testedEffect.applyOnAdjacentEgg(mockedCard, player.elementSpaceLogic, elementBag);
            testedEffect.applyOnAdjacentEgg(mockedCard, eggHatchingLogic);
            testedEffect.applyOnAdjacentEgg(mockedCard, handlerMovementLogic);
            testedEffect.applyOnAdjacentEgg(mockedCard, deck, handlerMovementLogic, hatchingGround);


        }

        this.replayAll();

        testedEffect.on(centerCard).on(hatchingGround).on(elementBag).on(eggHatchingLogic).on(player).on(deck)
                .on(handlerMovementLogic).apply();
    }

}

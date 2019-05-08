package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedEffect;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.player.FakePlayer;
import underlings.player.Player;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test
    // @Ignore
    public void testHatchOneAdjacentUnclaimedEgg() {
        Card centerCard = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(1);
        Handler handler = EasyMock.mock(Handler.class);
        mockedCards.get(0).wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect =
                new HatchAllUnclaimedEffect();
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player fakePlayer = FakePlayer.getInstance();
        Gui gui = EasyMock.mock(Gui.class);
        mockedCards.get(0).wildEffects[0].on(centerCard).on(hatchingGround).on(elementBag)
                .on(fakePlayer.elementSpaceLogic).on(fakePlayer).apply();

        EasyMock.replay(centerCard, hatchingGround, handler, elementBag, effect, gui);

        hatchAllUnclaimedAdjacentEggsEffect.apply();

        EasyMock.verify(centerCard, hatchingGround, handler, elementBag, effect, gui);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

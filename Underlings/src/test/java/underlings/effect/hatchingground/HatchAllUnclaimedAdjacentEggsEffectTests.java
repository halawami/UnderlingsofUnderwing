package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedEffect;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.player.Player;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test
    @Ignore
    public void testHatchOneAdjacentUnclaimedEgg() {
        Card centerCard = new Card();
        List<Card> mockedCards = getMockedCards(1);
        Handler handler = EasyMock.mock(Handler.class);
        mockedCards.get(0).wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        EasyMock.expect(mockedCards.get(0).wildEffects[0].on(elementBag)).andReturn(mockedCards.get(0).wildEffects[0]);
        EasyMock.expect(mockedCards.get(0).wildEffects[0].on(hatchingGround))
                .andReturn(mockedCards.get(0).wildEffects[0]);
        Player fakePlayer = EasyMock.mock(Player.class);
        EasyMock.expect(mockedCards.get(0).wildEffects[0].on(fakePlayer.elementSpaceLogic))
                .andReturn(mockedCards.get(0).wildEffects[0]);
        EasyMock.expect(mockedCards.get(0).wildEffects[0].on(fakePlayer)).andReturn(mockedCards.get(0).wildEffects[0]);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(mockedCards.get(0).wildEffects[0].on(gui)).andReturn(mockedCards.get(0).wildEffects[0]);
        mockedCards.get(0).wildEffects[0].apply();

        EasyMock.replay(hatchingGround, handler, elementBag, effect, gui, fakePlayer);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        hatchAllUnclaimedAdjacentEggsEffect.apply();

        EasyMock.verify(hatchingGround, handler, elementBag, effect, gui, fakePlayer);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

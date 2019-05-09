package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

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
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.player.Player;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test // TODO: ask Mohammad if this is the correct way to test this class
    public void testHatchOneAdjacentUnclaimedEgg() {
        Card centerCard = new Card();
        List<Card> mockedCards = getMockedCards(1);
        mockedCards.get(0).wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        mockedCards.get(0).handler = null;
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        // EasyMock.expect(hatchingGround.getAdjacentCards(centerCard)).andReturn(mockedCards);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        EasyMock.expect(effect.on(elementBag)).andReturn(effect);
        EasyMock.expect(effect.on(hatchingGround)).andReturn(effect);
        Player fakePlayer = FakePlayer.getInstance();
        EasyMock.expect(effect.on(fakePlayer.elementSpaceLogic)).andReturn(effect);
        EasyMock.expect(effect.on(fakePlayer)).andReturn(effect);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(effect.on(gui)).andReturn(effect);
        effect.apply();
        gui.notifyAction(-1, "EasyMock for class underlings.card.effect.Effect has been applied");

        EasyMock.replay(hatchingGround, elementBag, effect, gui);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        // hatchAllUnclaimedAdjacentEggsEffect.apply();
        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, hatchingGround, gui);
        assertEquals(mockedCards.get(0).handler, WildHandler.getInstance());
        EasyMock.verify(hatchingGround, elementBag, effect, gui);
    }

    @Test
    public void testAttemptHatchClaimedEgg() {
        Card centerCard = new Card();
        List<Card> mockedCards = getMockedCards(1);
        mockedCards.get(0).wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        mockedCards.get(0).handler = EasyMock.mock(Handler.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);

        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player fakePlayer = FakePlayer.getInstance();
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.replay(hatchingGround, elementBag, effect, gui);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();

        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, hatchingGround, gui);
        EasyMock.verify(hatchingGround, elementBag, effect, gui);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

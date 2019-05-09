package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Family;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.adjacenteggs.HatchAllUnclaimedEffect;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class HatchAllUnclaimedAdjacentEggsEffectTests {

    @Test // TODO: ask Mohammad if this is the correct way to test this class
    public void testHatchOneAdjacentUnclaimedEgg() {
        List<Card> mockedCards = getMockedCards(1);
        mockedCards.get(0).wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        mockedCards.get(0).family = Family.MONOCHROMATIC;
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player fakePlayer = FakePlayer.getInstance();
        eggHatchingLogic.hatchEgg(mockedCards.get(0), true, fakePlayer);
        Gui gui = EasyMock.mock(Gui.class);

        EasyMock.replay(hatchingGround, elementBag, effect, gui, eggHatchingLogic);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        hatchAllUnclaimedAdjacentEggsEffect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};
        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, hatchingGround, gui, eggHatchingLogic);
        EasyMock.verify(hatchingGround, elementBag, effect, gui, eggHatchingLogic);
    }

    @Test
    public void testAttemptToHatchClaimedEgg() {
        Card centerCard = new Card();
        List<Card> mockedCards = getMockedCards(1);
        mockedCards.get(0).wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        mockedCards.get(0).handler = EasyMock.mock(Handler.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player fakePlayer = FakePlayer.getInstance();
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.replay(hatchingGround, elementBag, effect, gui, eggHatchingLogic);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        hatchAllUnclaimedAdjacentEggsEffect.dragonFamilies = new Family[0];
        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, hatchingGround, gui, eggHatchingLogic);
        EasyMock.verify(hatchingGround, elementBag, effect, gui, eggHatchingLogic);
    }

    @Test
    public void testAttemptToHatchDifferentFamilyDragon() {
        Card centerCard = new Card();
        List<Card> mockedCards = getMockedCards(1);
        mockedCards.get(0).wildEffects = new Effect[1];
        mockedCards.get(0).family = Family.TRIADIC;
        Effect effect = EasyMock.mock(Effect.class);
        mockedCards.get(0).wildEffects[0] = effect;
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Player fakePlayer = FakePlayer.getInstance();
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.replay(hatchingGround, elementBag, effect, gui, eggHatchingLogic);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        hatchAllUnclaimedAdjacentEggsEffect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};

        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, hatchingGround, gui, eggHatchingLogic);
        EasyMock.verify(hatchingGround, elementBag, effect, gui, eggHatchingLogic);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}

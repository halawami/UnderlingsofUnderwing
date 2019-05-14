package underlings.effect.hatchingground;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
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

    private List<Card> mockedCards;
    private Effect effect;
    private EggHatchingLogic eggHatchingLogic;
    private HatchingGround hatchingGround;
    private ElementBag elementBag;
    private Player fakePlayer;
    private Gui gui;
    private HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect;


    @Before
    public void init() throws IOException {
        this.mockedCards = getMockedCards(1);
        this.mockedCards.get(0).wildEffects = new Effect[1];
        this.effect = EasyMock.mock(Effect.class);
        this.mockedCards.get(0).wildEffects[0] = effect;
        this.eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.elementBag = EasyMock.mock(ElementBag.class);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        this.fakePlayer = FakePlayer.getInstance();
        this.gui = EasyMock.mock(Gui.class);
        this.hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
    }

    @Test // TODO: ask Mohammad if this is the correct way to test this class
    public void testHatchOneAdjacentUnclaimedEgg() {
        mockedCards.get(0).family = Family.MONOCHROMATIC;
        eggHatchingLogic.hatchEgg(mockedCards.get(0), true, fakePlayer);

        EasyMock.replay(hatchingGround, elementBag, effect, gui, eggHatchingLogic);

        hatchAllUnclaimedAdjacentEggsEffect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};
        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, eggHatchingLogic);

        EasyMock.verify(hatchingGround, elementBag, effect, gui, eggHatchingLogic);
    }

    @Test
    public void testAttemptToHatchClaimedEgg() {
        mockedCards.get(0).handler = EasyMock.mock(Handler.class);

        EasyMock.replay(hatchingGround, elementBag, effect, gui, eggHatchingLogic);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        hatchAllUnclaimedAdjacentEggsEffect.dragonFamilies = new Family[0];
        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, eggHatchingLogic);
        EasyMock.verify(hatchingGround, elementBag, effect, gui, eggHatchingLogic);
    }

    @Test
    public void testAttemptToHatchDifferentFamilyDragon() {
        mockedCards.get(0).family = Family.TRIADIC;

        EasyMock.replay(hatchingGround, elementBag, effect, gui, eggHatchingLogic);

        HatchAllUnclaimedEffect hatchAllUnclaimedAdjacentEggsEffect = new HatchAllUnclaimedEffect();
        hatchAllUnclaimedAdjacentEggsEffect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};

        hatchAllUnclaimedAdjacentEggsEffect.applyOnAdjacentEgg(mockedCards.get(0), elementBag,
                fakePlayer.elementSpaceLogic, eggHatchingLogic);

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

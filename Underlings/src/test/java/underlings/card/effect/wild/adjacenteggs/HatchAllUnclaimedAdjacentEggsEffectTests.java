package underlings.card.effect.wild.adjacenteggs;

import static org.junit.Assert.assertEquals;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.Family;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.hatchingground.EggHatchingUtilities;
import underlings.hatchingground.HatchingGround;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleUtilities;

public class HatchAllUnclaimedAdjacentEggsEffectTests extends MockTest {

    HatchAllUnclaimedEffect effect;

    @Before
    public void init() throws IOException {
        this.cards = this.mockListOf(Card.class).withLengthOf(1);
        this.cards.get(0).wildEffects = new Effect[1];
        this.cards.get(0).wildEffects[0] = this.effect;
        this.eggHatchingLogic = this.mock(EggHatchingUtilities.class);
        this.hatchingGround = this.mock(HatchingGround.class);
        this.elementBag = this.mock(ElementBag.class);
        List<String> recipes =
                Resources.readLines(Resources.getResource(LocaleUtilities.get("default_recipe_list")), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        this.player = FakePlayer.getInstance();
        this.gui = this.mock(Gui.class);
        this.effect = new HatchAllUnclaimedEffect();
    }

    @Test
    public void testHatchOneAdjacentUnclaimedEgg() {
        this.cards.get(0).family = Family.MONOCHROMATIC;
        this.eggHatchingLogic.hatchEgg(this.cards.get(0), this.player);

        this.replayAll();

        this.effect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};

        this.effect.applyOnAdjacentEgg(this.cards.get(0), this.eggHatchingLogic);
    }

    @Test
    public void testAttemptToHatchClaimedEgg() {
        this.cards.get(0).handler = this.mock(Handler.class);

        this.replayAll();

        this.effect.dragonFamilies = new Family[0];
        this.effect.applyOnAdjacentEgg(this.cards.get(0), this.eggHatchingLogic);
    }

    @Test
    public void testAttemptToHatchDifferentFamilyDragon() {
        this.cards.get(0).family = Family.TRIADIC;

        this.replayAll();

        this.effect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};
        this.effect.applyOnAdjacentEgg(this.cards.get(0), this.eggHatchingLogic);
    }

    @Test
    public void testToString() {
        this.replayAll();
        HatchAllUnclaimedEffect effect = new HatchAllUnclaimedEffect();
        effect.dragonFamilies = new Family[] {Family.MONOCHROMATIC};
        StringBuilder families = new StringBuilder();
        for (Family family : effect.dragonFamilies) {
            families.append(family);
            families.append(" ");
        }
        assertEquals(LocaleUtilities.format("hatch_all_unclaimed_adjacent_eggs_effect", families), effect.toString());
    }
}

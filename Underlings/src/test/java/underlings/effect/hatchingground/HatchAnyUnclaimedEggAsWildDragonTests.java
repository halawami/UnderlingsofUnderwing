package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.playerhatchingground.HatchAnyUnclaimedEggAsWildDragon;
import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.gui.YesNoChoice;
import underlings.hatchingground.HatchingGround;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAnyUnclaimedEggAsWildDragonTests extends MockTest {

    @Before
    public void init() {
        this.card = this.mock(Card.class);
        this.hatchingGround = this.mock(HatchingGround.class);
        this.elementBag = this.mock(ElementBag.class);
        this.gui = this.mock(Gui.class);
        this.player = this.mock(Player.class);
        this.eggHatchingLogic = this.mock(EggHatchingLogic.class);
        this.effect = new HatchAnyUnclaimedEggAsWildDragon();
    }

    @Test
    public void testApplyEffect() throws IOException {
        EasyMock.expect(this.player.getId()).andReturn(-1);
        EasyMock.expect(
                this.gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(), -1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList(this.card));
        EasyMock.expect(this.gui.promptChoice(LocaleWrap.get("prompt_card_hatch_wildly"), Arrays.asList(this.card), 0))
                .andReturn(this.card);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        this.eggHatchingLogic.hatchEgg(this.card, FakePlayer.getInstance());

        this.replayAll();

        this.effect.on(this.card).on(this.hatchingGround).on(this.elementBag).on(this.gui).on(this.player)
                .on(this.eggHatchingLogic).apply();
    }

    @Test
    public void testNotWantingToHatch() {
        EasyMock.expect(this.player.getId()).andReturn(-1);
        EasyMock.expect(
                this.gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(), -1))
                .andReturn(YesNoChoice.NO);

        this.replayAll();

        this.effect.on(this.card).on(this.hatchingGround).on(this.elementBag).on(this.gui).on(this.player)
                .on(this.eggHatchingLogic).apply();
    }

    @Test
    public void testApplyNoUnclaimedEggs() {
        EasyMock.expect(this.player.getId()).andReturn(-1).times(2);
        EasyMock.expect(
                this.gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(), -1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList());
        this.gui.notifyAction(-1, LocaleWrap.get("notify_no_unclaimed_eggs"));

        this.replayAll();

        this.effect.on(this.card).on(this.hatchingGround).on(this.elementBag).on(this.gui).on(this.player)
                .on(this.eggHatchingLogic).apply();
    }

    @Test
    public void testToString() {
        this.replayAll();
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();
        assertEquals(LocaleWrap.get("hatch_egg_as_wild_dragon_effect"), effect.toString());
    }

}

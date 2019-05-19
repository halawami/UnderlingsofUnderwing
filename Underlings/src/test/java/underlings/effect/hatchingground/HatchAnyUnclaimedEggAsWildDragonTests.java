package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.HatchAnyUnclaimedEggAsWildDragon;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.YesNoChoice;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAnyUnclaimedEggAsWildDragonTests extends MockTest {

    @Test
    public void testApplyEffect() throws IOException {
        Card card = this.mock(Card.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        ElementBag elementBag = this.mock(ElementBag.class);
        Gui gui = this.mock(Gui.class);
        Player player = this.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();

        EasyMock.expect(player.getId()).andReturn(-1);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(), -1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList(card));
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_hatch_wildly"), Arrays.asList(card), 0))
                .andReturn(card);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        eggHatchingLogic.hatchEgg(card, FakePlayer.getInstance());

        this.replayAll();

        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic).apply();
    }

    @Test
    public void testNotWantingToHatch() {
        Card card = this.mock(Card.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        ElementBag elementBag = this.mock(ElementBag.class);
        Gui gui = this.mock(Gui.class);
        Player player = this.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();

        EasyMock.expect(player.getId()).andReturn(-1);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(), -1))
                .andReturn(YesNoChoice.NO);

        this.replayAll();

        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic).apply();
    }

    @Test
    public void testApplyNoUnclaimedEggs() {
        Card card = this.mock(Card.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        ElementBag elementBag = this.mock(ElementBag.class);
        Gui gui = this.mock(Gui.class);
        Player player = this.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();

        EasyMock.expect(player.getId()).andReturn(-1).times(2);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(), -1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList());
        gui.notifyAction(-1, LocaleWrap.get("notify_no_unclaimed_eggs"));

        this.replayAll();

        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();
        assertEquals(LocaleWrap.get("hatch_egg_as_wild_dragon_effect"), effect.toString());
    }

}

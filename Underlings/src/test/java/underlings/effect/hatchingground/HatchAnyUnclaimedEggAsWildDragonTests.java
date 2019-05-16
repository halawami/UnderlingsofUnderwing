package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.HatchAnyUnclaimedEggAsWildDragon;
import underlings.card.effect.domestic.HatchAnyUnclaimedEggAsWildDragon.Choice;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAnyUnclaimedEggAsWildDragonTests {

    @Test
    public void testApplyEffect() throws IOException {
        Card card = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Gui gui = EasyMock.mock(Gui.class);
        Player player = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();
        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic);
        EasyMock.expect(player.getId()).andReturn(-1);
        EasyMock.expect(gui.promptDecision(LocaleWrap.get("prompt_choice_hatch_wildly"), -1)).andReturn(true);
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList(card));
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_hatch_wildly"), Arrays.asList(card), 0))
                .andReturn(card);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        eggHatchingLogic.hatchEgg(card, true, FakePlayer.getInstance());

        EasyMock.replay(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);

        effect.apply();

        EasyMock.verify(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);
    }

    @Test
    public void testNotWantingToHatch() {
        Card card = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Gui gui = EasyMock.mock(Gui.class);
        Player player = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();
        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic);
        EasyMock.expect(player.getId()).andReturn(-1);
        EasyMock.expect(gui.promptDecision(LocaleWrap.get("prompt_choice_hatch_wildly"), -1)).andReturn(false);

        EasyMock.replay(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);

        effect.apply();

        EasyMock.verify(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);
    }

    @Test
    public void testApplyNoUnclaimedEggs() {
        Card card = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Gui gui = EasyMock.mock(Gui.class);
        Player player = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();
        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic);
        EasyMock.expect(player.getId()).andReturn(-1).times(2);
        EasyMock.expect(gui.promptDecision(LocaleWrap.get("prompt_choice_hatch_wildly"), -1)).andReturn(true);
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList());
        gui.notifyAction(-1, LocaleWrap.get("notify_no_unclaimed_eggs"));

        EasyMock.replay(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);

        effect.apply();

        EasyMock.verify(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);
    }

    @Test
    public void testChoiceYesToString() {
        Choice choice = Choice.YES;
        assertEquals("Yes", choice.toString());
    }

}

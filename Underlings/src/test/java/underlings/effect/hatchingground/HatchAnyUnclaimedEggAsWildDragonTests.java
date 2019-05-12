package underlings.effect.hatchingground;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.HatchAnyUnclaimedEggAsWildDragon;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class HatchAnyUnclaimedEggAsWildDragonTests {

    @Test
    public void testApplyEffect() {
        Card card = EasyMock.mock(Card.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Gui gui = EasyMock.mock(Gui.class);
        Player player = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Effect effect = new HatchAnyUnclaimedEggAsWildDragon();
        effect.on(card).on(hatchingGround).on(elementBag).on(gui).on(player).on(eggHatchingLogic);
        EasyMock.expect(player.getPlayerId()).andReturn(-1);
        EasyMock.expect(gui.promptDecision("Would you like to hatch unclaimed egg as wild dragon", -1)).andReturn(true);
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList(card));
        EasyMock.expect(gui.promptCard("Choose a card to hatch wildly", Arrays.asList(card))).andReturn(card);
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
        EasyMock.expect(player.getPlayerId()).andReturn(-1);
        EasyMock.expect(gui.promptDecision("Would you like to hatch unclaimed egg as wild dragon", -1))
                .andReturn(false);

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
        EasyMock.expect(player.getPlayerId()).andReturn(-1);
        EasyMock.expect(gui.promptDecision("Would you like to hatch unclaimed egg as wild dragon", -1)).andReturn(true);
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList());
        gui.notifyAction(0, "No unclaimed eggs to wildly hatch");
        eggHatchingLogic.hatchEgg(card, true, FakePlayer.getInstance());

        EasyMock.replay(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);

        effect.apply();

        EasyMock.verify(card, hatchingGround, elementBag, gui, player, eggHatchingLogic);
    }
}

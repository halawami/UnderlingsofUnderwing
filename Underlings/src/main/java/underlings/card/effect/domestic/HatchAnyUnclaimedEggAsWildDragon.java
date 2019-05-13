package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAnyUnclaimedEggAsWildDragon extends HatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        boolean choice = gui.promptDecision(LocaleWrap.get("prompt_choice_hatch_wildly"), currentPlayer.getPlayerId());
        if (choice) {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (unclaimedEggs.size() == 0) {
                gui.notifyAction(currentPlayer.getPlayerId(), LocaleWrap.get("notify_no_unclaimed_eggs"));
            } else {
                Card toHatch = gui.promptCard(LocaleWrap.get("prompt_card_hatch_wildly"), unclaimedEggs);
                eggHatchingLogic.hatchEgg(toHatch, true, FakePlayer.getInstance());
            }
        }
    }

}

package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.YesNoChoice;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAnyUnclaimedEggAsWildDragon extends PlayerHatchingGroundEffect {

    @Override
    public String toString() {
        return LocaleWrap.get("hatch_egg_as_wild_dragon_effect");
    }

    @Override
    protected void apply(HatchingGround hatchingGround, EggHatchingLogic hatchingLogic, Player currentPlayer, Gui gui) {
        YesNoChoice choice = gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), YesNoChoice.getChoices(),
                currentPlayer.getId());

        if (choice == YesNoChoice.YES) {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (unclaimedEggs.size() == 0) {
                gui.notifyAction(currentPlayer.getId(), LocaleWrap.get("notify_no_unclaimed_eggs"));
            } else {
                Card toHatch = gui
                        .promptChoice(LocaleWrap.get("prompt_card_hatch_wildly"), unclaimedEggs, currentPlayer.id);
                hatchingLogic.hatchEgg(toHatch, FakePlayer.getInstance());
            }
        }
    }
}

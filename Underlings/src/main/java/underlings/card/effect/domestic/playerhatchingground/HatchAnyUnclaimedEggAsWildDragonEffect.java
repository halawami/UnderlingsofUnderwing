package underlings.card.effect.domestic.playerhatchingground;

import java.util.List;
import underlings.card.Card;
import underlings.gui.Gui;
import underlings.gui.YesNoChoice;
import underlings.hatchingground.EggHatchingUtilities;
import underlings.hatchingground.HatchingGround;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class HatchAnyUnclaimedEggAsWildDragonEffect extends PlayerHatchingGroundEffect {

    @Override
    public String toString() {
        return LocaleUtilities.get("hatch_egg_as_wild_dragon_effect");
    }

    @Override
    protected void apply(HatchingGround hatchingGround, EggHatchingUtilities hatchingLogic, Player currentPlayer,
            Gui gui) {
        YesNoChoice choice = gui.promptChoice(LocaleUtilities.get("prompt_choice_hatch_wildly"),
                YesNoChoice.getChoices(), currentPlayer.getId());
        if (choice == YesNoChoice.YES) {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (unclaimedEggs.size() == 0) {
                gui.notifyAction(currentPlayer.getId(), LocaleUtilities.get("notify_no_unclaimed_eggs"));
            } else {
                Card toHatch = gui.promptChoice(LocaleUtilities.get("prompt_card_hatch_wildly"), unclaimedEggs,
                        currentPlayer.id);
                hatchingLogic.hatchEgg(toHatch, FakePlayer.getInstance());
            }
        }
    }
}

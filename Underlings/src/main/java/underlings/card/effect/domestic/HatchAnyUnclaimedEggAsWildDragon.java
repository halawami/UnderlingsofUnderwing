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

public class HatchAnyUnclaimedEggAsWildDragon extends HatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        boolean choice =
                gui.promptDecision("Would you like to hatch unclaimed egg as wild dragon", currentPlayer.getPlayerId());
        if (choice) {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (unclaimedEggs.size() == 0) {
                gui.notifyAction(currentPlayer.getPlayerId(), "No unclaimed eggs to wildly hatch");
            } else {
                Card toHatch = gui.promptCard("Choose a card to hatch wildly", unclaimedEggs);
                eggHatchingLogic.hatchEgg(toHatch, true, FakePlayer.getInstance());
            }
        }
    }

}

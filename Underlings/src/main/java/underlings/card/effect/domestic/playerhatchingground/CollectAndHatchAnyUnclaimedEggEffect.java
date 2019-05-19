package underlings.card.effect.domestic.playerhatchingground;

import java.util.List;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.LocaleUtilities;

public class CollectAndHatchAnyUnclaimedEggEffect extends PlayerHatchingGroundEffect {

    public int points;

    @Override
    protected void apply(HatchingGround hatchingGround, EggHatchingUtilities hatchingLogic, Player currentPlayer,
            Gui gui) {
        List<Card> validCards = hatchingGround.getDragons(this.points, true);

        Card selectedCard =
                gui.getCard(currentPlayer.getId(), LocaleUtilities.get("gui_card"), hatchingGround, validCards);

        if (selectedCard != EmptyCard.getInstance()) {
            hatchingLogic.hatchEgg(selectedCard, currentPlayer);
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.format("hatch_unclaimed_dragon_effect", this.points);
    }

}

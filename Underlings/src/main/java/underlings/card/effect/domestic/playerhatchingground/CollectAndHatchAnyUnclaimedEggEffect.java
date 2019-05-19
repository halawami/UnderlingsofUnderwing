package underlings.card.effect.domestic.playerhatchingground;

import java.util.List;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class CollectAndHatchAnyUnclaimedEggEffect extends PlayerHatchingGroundEffect {

    public int points;

    @Override
    protected void apply(HatchingGround hatchingGround, EggHatchingLogic hatchingLogic, Player currentPlayer, Gui gui) {
        List<Card> validCards = hatchingGround.getDragons(this.points, true);

        Card selectedCard = gui.getCard(currentPlayer.getId(), LocaleWrap.get("gui_card"), hatchingGround, validCards);

        if (selectedCard != EmptyCard.getInstance()) {
            hatchingLogic.hatchEgg(selectedCard, currentPlayer);
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.format("hatch_unclaimed_dragon_effect", this.points);
    }

}

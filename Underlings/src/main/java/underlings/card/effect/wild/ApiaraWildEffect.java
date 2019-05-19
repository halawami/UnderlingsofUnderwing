package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.hatchingground.Deck;
import underlings.hatchingground.EggHatchingUtilities;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class ApiaraWildEffect extends Effect {

    private boolean redraw;

    public ApiaraWildEffect() {
        this.redraw = false;
    }

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, Player currentPlayer,
            EggHatchingUtilities eggHatchingLogic, Deck deck) {
        if (!this.redraw) {
            hatchingGround.replaceCard(centerCard);
            deck.addCard(centerCard, true);
            this.redraw = true;
        } else {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (!unclaimedEggs.isEmpty()) {
                for (Card egg : unclaimedEggs) {
                    eggHatchingLogic.hatchEgg(egg, currentPlayer);
                }
            }
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("apiara_wild_effect");
    }
}

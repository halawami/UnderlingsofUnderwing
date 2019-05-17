package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DiverseHatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class ApiaraWildEffect extends DiverseHatchingGroundEffect {

    private boolean redraw;

    public ApiaraWildEffect() {
        this.redraw = false;
    }

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        if (!this.redraw) {
            hatchingGround.replaceCard(centerCard);
            deck.addCard(centerCard);
            this.redraw = true;
        } else {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (!unclaimedEggs.isEmpty()) {
                for (Card egg : unclaimedEggs) {
                    eggHatchingLogic.hatchEgg(egg, true, currentPlayer);
                }
            }
        }
    }

}

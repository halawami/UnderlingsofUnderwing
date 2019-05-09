package underlings.phase;

import java.util.List;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class DragonPhase extends SequentialPhase {

    private List<Card> completeEggs;

    public DragonPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    @Override
    public void setup() {
        this.completeEggs = this.hatchingGround.pullAndReplaceCompleteEggs();
    }

    @Override
    public void turn(Player player) {
        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, player);
        for (Card card : player.unhatchedCards) {
            eggHatchingLogic.hatchEgg(card, false);
        }
        for (Card completeCard : this.completeEggs) {
            eggHatchingLogic.returnElementsToBag(completeCard);
            if (player.hasCard(completeCard)) {
                this.gui.notifyAction(player.getPlayerId(), completeCard.name + " is going to incubation state");
            }
        }
        this.phaseComplete = true;
    }

}

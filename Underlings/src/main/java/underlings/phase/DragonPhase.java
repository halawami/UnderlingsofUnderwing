package underlings.phase;

import java.util.List;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class DragonPhase extends SequentialPhase {

    private List<Card> completeEggs;
    private EggHatchingLogic domesticEggHatchingLogic;

    public DragonPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field, EggHatchingLogic eggHatchingLogic) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
        this.domesticEggHatchingLogic = eggHatchingLogic;
    }

    @Override
    public void setup() {
        this.completeEggs = this.hatchingGround.pullAndReplaceCompleteEggs();
    }

    @Override
    public void turn(Player player) {
        for (Card card : player.unhatchedCards) {
            this.domesticEggHatchingLogic.hatchEgg(card, false, player);
        }
        player.unhatchedCards.clear();
        for (Card completeCard : this.completeEggs) {
            this.domesticEggHatchingLogic.returnElementsToBag(completeCard);
            if (player.getHandlers().contains(completeCard.handler)) {
                if (player.hatchingTime == 0) {
                    this.domesticEggHatchingLogic.hatchEgg(completeCard, false, player);
                } else {
                    player.moveToIncubation(completeCard);
                    this.gui.notifyAction(player.getPlayerId(),
                            LocaleWrap.format("incubation_state", completeCard.name));
                }
            }
        }
        this.setPhaseComplete(true);
    }

}

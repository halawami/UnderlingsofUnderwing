package underlings.phase;

import java.util.LinkedList;
import java.util.List;
import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
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
        List<Card> hatchedCards = new LinkedList<>();
        for (Card card : player.unhatchedCards.keySet()) {
            if (player.unhatchedCards.get(card) == 1) {
                this.domesticEggHatchingLogic.hatchEgg(card, player);
                hatchedCards.add(card);
            } else {
                player.unhatchedCards.put(card, player.unhatchedCards.get(card) - 1);
            }
        }
        for (Card card : hatchedCards) {
            player.unhatchedCards.remove(card);
        }
        for (Card completeCard : this.completeEggs) {
            this.domesticEggHatchingLogic.returnElementsToBag(completeCard);
            if (player.handlers.contains(completeCard.handler)) {
                if (player.hatchingTime == 0) {
                    this.domesticEggHatchingLogic.hatchEgg(completeCard, player);
                    player.hatchingTime = 1;
                } else if (this.hatchingGround.lateHatching) {
                    player.moveToIncubation(completeCard, player.hatchingTime + 1);
                    this.gui.notifyAction(player.getId(), LocaleWrap.format("incubation_state", completeCard.name));
                } else {
                    player.moveToIncubation(completeCard, player.hatchingTime);
                    this.gui.notifyAction(player.getId(), LocaleWrap.format("incubation_state", completeCard.name));
                }
            }
        }
        this.setPhaseComplete(true);
    }

    @Override
    public void teardown() {
        return;
    }

}

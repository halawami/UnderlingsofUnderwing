package underlings.phase;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class DragonPhase extends SequentialPhase {

    private List<Card> completeEggs;

    public DragonPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    @Override
    public void setup() {
        this.completeEggs = this.hatchingGround.pullAndReplaceCompleteEggs();
        for (Card completeEgg : this.completeEggs) {
            returnElementsToBag(completeEgg);
        }
    }

    private void returnElementsToBag(Card dragon) {
        for (ElementSpace space : dragon.elementSpaces) {
            for (ElementColor color : space.elements) {
                this.elementBag.putElement(color);
            }
        }
    }

    @Override
    // TODO fix the problem with builder pattern, call all possible parameters
    public void turn(Player player) {
        for (Effect domesticEffect : player.getAllEffects()) {
            domesticEffect.on(elementBag).on(player).apply();
            this.gui.notifyAction(player.getPlayerId(), domesticEffect.toString() + " has been applied");
        }
        for (Card completeCard : this.completeEggs) {
            if (player.hasCard(completeCard)) {
                this.gui.notifyAction(player.getPlayerId(), completeCard.name + " is going to incubation state");
            }
        }
        this.phaseComplete = true;
    }

}

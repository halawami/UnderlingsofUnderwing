package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class CollectOneElementFromAnyEggInPlayEffect extends HatchingGroundEffect {

    public ElementColor[] elementChoices;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        List<Card> allCards = hatchingGround.getAllCards();
        ElementSpace selectedSpace = gui.getElementSpaceContainingElementOfColors(allCards, this.elementChoices);
        ElementColor selectedElement =
                gui.getElementOfColorsFromSpace(this.elementChoices, selectedSpace, currentPlayer.getPlayerId());
        giveElementToPlayer(selectedElement, selectedSpace, currentPlayer);

    }

    private void giveElementToPlayer(ElementColor selectedElement, ElementSpace selectedSpace, Player currentPlayer) {
        selectedSpace.destroyOneElementOfColor(selectedElement);
        currentPlayer.addElement(selectedElement);
    }
}

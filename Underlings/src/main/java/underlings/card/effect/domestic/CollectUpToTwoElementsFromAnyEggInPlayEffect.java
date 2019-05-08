package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class CollectUpToTwoElementsFromAnyEggInPlayEffect extends HatchingGroundEffect {

    public ElementColor[] elementChoices;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, Gui gui, Player currentPlayer) {
        List<Card> allCards = hatchingGround.getAllCards();
        for (int i = 0; i < 2; i++) {
            ElementColor selectedColor = gui.getColorFromList(this.elementChoices);
            ElementSpace selectedSpace = gui.getElementSpaceContainingElementOfColor(allCards, selectedColor);
            Element selectedElement = selectedSpace.getElementOfColor(selectedColor);
        }
    }
}

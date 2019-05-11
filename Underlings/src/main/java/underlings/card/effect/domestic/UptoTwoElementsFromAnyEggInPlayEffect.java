package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class UptoTwoElementsFromAnyEggInPlayEffect extends HatchingGroundEffect {

    public ElementColor[] elementChoices;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        List<Card> allCards = hatchingGround.getAllCards();
        for (int i = 0; i < 2; i++) {
            ElementSpace selectedSpace = gui.getElementSpaceContainingElementOfColors(allCards, this.elementChoices);
            Element selectedElement = gui.getElementOfColorsFromSpace(this.elementChoices, selectedSpace);
            applyOnSelectedElement(selectedElement, selectedSpace, currentPlayer);
        }
    }

    public abstract void applyOnSelectedElement(Element selectedElement, ElementSpace selectedSpace,
            Player currentPlayer);
}

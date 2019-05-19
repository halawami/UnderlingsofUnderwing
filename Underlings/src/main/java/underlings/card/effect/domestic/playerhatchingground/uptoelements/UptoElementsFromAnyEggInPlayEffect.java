package underlings.card.effect.domestic.playerhatchingground.uptoelements;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.domestic.playerhatchingground.PlayerHatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.gui.Gui;
import underlings.hatchingground.EggHatchingUtilities;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public abstract class UptoElementsFromAnyEggInPlayEffect extends PlayerHatchingGroundEffect {

    public ElementColor[] elementChoices;
    public int upTo;

    @Override
    protected void apply(HatchingGround hatchingGround, EggHatchingUtilities hatchingLogic, Player currentPlayer, Gui gui) {
        List<Card> allCards = hatchingGround.getAllCards();

        for (int i = 0; i < this.upTo; i++) {
            ElementSpace selectedSpace =
                    gui.getElementSpaceWithColors(allCards, this.elementChoices, currentPlayer.getId());
            if (selectedSpace == null) {
                return;
            }
            Element selectedElement =
                    gui.getElementOfColorsFromSpace(this.elementChoices, selectedSpace, currentPlayer.getId());
            this.applyOnSelectedElement(selectedElement, selectedSpace, currentPlayer);
        }
    }


    public abstract void applyOnSelectedElement(Element selectedElement, ElementSpace selectedSpace,
            Player currentPlayer);
}

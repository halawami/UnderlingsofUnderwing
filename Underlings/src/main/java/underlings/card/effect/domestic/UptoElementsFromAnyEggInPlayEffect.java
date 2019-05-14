package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class UptoElementsFromAnyEggInPlayEffect extends HatchingGroundEffect {

    public ElementColor[] elementChoices;
    public int upTo;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> allCards = hatchingGround.getAllCards();
        for (int i = 0; i < this.upTo; i++) {
            ElementSpace selectedSpace = gui.getElementSpaceContainingElementOfColors(allCards, this.elementChoices);
            Element selectedElement =
                    gui.getElementOfColorsFromSpace(this.elementChoices, selectedSpace, currentPlayer.getPlayerId());
            applyOnSelectedElement(selectedElement, selectedSpace, currentPlayer);
        }
    }


    public abstract void applyOnSelectedElement(Element selectedElement, ElementSpace selectedSpace,
            Player currentPlayer);
}

package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DiverseHatchingGroundEffect;
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

public class CollectOneElementFromAnyEggInPlayEffect extends DiverseHatchingGroundEffect {

    public ElementColor[] elementChoices;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> allCards = hatchingGround.getAllCards();
        ElementSpace selectedSpace = gui.getElementSpaceContainingElementOfColors(allCards, this.elementChoices);
        Element selectedElement =
                gui.getElementOfColorsFromSpace(this.elementChoices, selectedSpace, currentPlayer.getId());
        giveElementToPlayer(selectedElement, selectedSpace, currentPlayer);

    }

    private void giveElementToPlayer(Element selectedElement, ElementSpace selectedSpace, Player currentPlayer) {
        selectedSpace.destroyOneElementOfColor(selectedElement.getColor());
        currentPlayer.addElement(selectedElement);
    }
}

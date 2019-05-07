package underlings.card.effect.wild;

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

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, Gui gui) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            for (ElementColor elementColorToAdd : this.elementColors) {
                addElementToCard(elementColorToAdd, adjacentCard, elementSpaceLogic, elementBag);
            }
        }
    }

    public void addElementToCard(ElementColor elementColorToAdd, Card card, ElementSpaceLogic elementSpaceLogic,
            ElementBag elementBag) {
        List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(card, elementColorToAdd);
        if (!playableSpaces.isEmpty()) {
            Element elementToAdd = elementBag.drawElementFromList(elementColorToAdd);
            playableSpaces.get(0).addElements(elementToAdd);
        }
    }

    @Override
    public String toString() {
        return "Add elements to adjacent cards";
    }

}

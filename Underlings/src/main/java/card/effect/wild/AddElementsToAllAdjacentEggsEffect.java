package card.effect.wild;

import java.util.List;

import card.Card;
import card.effect.HatchingGroundEffect;
import element.Element;
import element.ElementBag;
import element.ElementColor;
import element.ElementSpace;
import element.utilities.ElementSpaceLogic;
import game.HatchingGround;

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic) {
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

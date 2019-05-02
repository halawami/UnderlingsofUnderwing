package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.List;

public class destroyAllElementsOnAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            for (ElementColor elementColorToDestroy : this.elementColors) {
                destroyAllElementsOfColorOnCard(elementColorToDestroy, adjacentCard, elementSpaceLogic);
            }
        }
    }

    public void destroyAllElementsOfColorOnCard(ElementColor elementColorToDestroy, Card card, ElementSpaceLogic elementSpaceLogic) {
        List<ElementSpace> destroyableElementSpaces = elementSpaceLogic.getDestroyableSpaces(card, elementColorToDestroy);
        if (!destroyableElementSpaces.isEmpty()) {
            destroyableElementSpaces.get(0).destroyAllElementsOfColor(elementColorToDestroy);
        }
    }
}

package underlings.card.effect.wild.destroy;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public abstract class ElementsOnAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            for (ElementColor elementColorToDestroy : this.elementColors) {
                destroyElementsOfColorOnCard(elementColorToDestroy, adjacentCard, elementSpaceLogic);
            }
        }
    }

    public void destroyElementsOfColorOnCard(ElementColor elementColorToDestroy, Card card,
            ElementSpaceLogic elementSpaceLogic) {
        List<ElementSpace> destroyableElementSpaces =
                elementSpaceLogic.getDestroyableSpaces(card, elementColorToDestroy);
        for (ElementSpace destroyableSpace : destroyableElementSpaces) {
            destroyElementsOfColorOnSpace(destroyableSpace, elementColorToDestroy);
        }
    }

    public abstract void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace,
            ElementColor elementColorToDestroy);
}

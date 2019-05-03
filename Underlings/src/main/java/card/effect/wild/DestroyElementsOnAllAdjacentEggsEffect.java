package card.effect.wild;

import java.util.List;

import card.Card;
import card.effect.HatchingGroundEffect;
import element.ElementBag;
import element.ElementColor;
import element.ElementSpace;
import element.utilities.ElementSpaceLogic;
import game.HatchingGround;

public abstract class DestroyElementsOnAllAdjacentEggsEffect extends HatchingGroundEffect {

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
        if (!destroyableElementSpaces.isEmpty()) {
            for (ElementSpace destroyableSpace : destroyableElementSpaces) {
                destroyElementsOfColorOnSpace(destroyableSpace, elementColorToDestroy);
            }
        }
    }

    public abstract void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace,
            ElementColor elementColorToDestroy);
}

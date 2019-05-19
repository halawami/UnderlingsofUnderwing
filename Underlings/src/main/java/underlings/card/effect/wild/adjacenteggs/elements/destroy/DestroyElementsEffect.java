package underlings.card.effect.wild.adjacenteggs.elements.destroy;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.elements.ElementsEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.utilities.ElementSpaceUtilities;

public abstract class DestroyElementsEffect extends ElementsEffect {

    public ElementColor[] elementColors;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementSpaceUtilities elementSpaceLogic, ElementBag elementBag) {
        this.destroyElementsOnAdjacentEgg(adjacentEgg, elementSpaceLogic);
    }

    private void destroyElementsOnAdjacentEgg(Card adjacentEgg, ElementSpaceUtilities elementSpaceLogic) {
        for (ElementColor elementColorToDestroy : this.elementColors) {
            this.destroyElementsOfColorOnCard(elementColorToDestroy, adjacentEgg, elementSpaceLogic);
        }
    }

    public void destroyElementsOfColorOnCard(ElementColor elementColorToDestroy, Card card,
            ElementSpaceUtilities elementSpaceLogic) {
        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, elementColorToDestroy);
        for (ElementSpace destroyableSpace : destroyableSpaces) {
            this.destroyElementsOfColorOnSpace(destroyableSpace, elementColorToDestroy);
        }
    }

    public abstract void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace,
            ElementColor elementColorToDestroy);
}

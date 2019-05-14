package underlings.card.effect.wild.adjacenteggs.destroy;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.gui.Gui;
import underlings.utilities.EggHatchingLogic;

public abstract class ElementsEffect extends AdjacentEggsEffect {

    public ElementColor[] elementColors;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            Gui gui, EggHatchingLogic eggHatchingLogic) {
        this.destroyElementsOnAdjacentEgg(adjacentEgg, elementSpaceLogic);
    }

    public void destroyElementsOnAdjacentEgg(Card adjacentEgg, ElementSpaceLogic elementSpaceLogic) {
        for (ElementColor elementColorToDestroy : this.elementColors) {
            destroyElementsOfColorOnCard(elementColorToDestroy, adjacentEgg, elementSpaceLogic);
        }
    }

    public void destroyElementsOfColorOnCard(ElementColor elementColorToDestroy, Card card,
            ElementSpaceLogic elementSpaceLogic) {
        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, elementColorToDestroy);
        for (ElementSpace destroyableSpace : destroyableSpaces) {
            destroyElementsOfColorOnSpace(destroyableSpace, elementColorToDestroy);
        }
    }

    public abstract void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace,
            ElementColor elementColorToDestroy);
}

package underlings.card.effect.wild.adjacenteggs.elements;

import underlings.card.Card;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.utilities.ElementSpaceUtilities;

public abstract class ElementsEffect extends AdjacentEggsEffect {

    @Override
    public abstract void applyOnAdjacentEgg(Card adjacentEgg, ElementSpaceUtilities elementSpaceLogic,
            ElementBag elementBag);


}

package underlings.card.effect.wild.adjacenteggs.elements;

import underlings.card.Card;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;

public abstract class ElementsEffect extends AdjacentEggsEffect {

    @Override
    public abstract void applyOnAdjacentEgg(Card adjacentEgg, ElementSpaceLogic elementSpaceLogic,
            ElementBag elementBag);


}

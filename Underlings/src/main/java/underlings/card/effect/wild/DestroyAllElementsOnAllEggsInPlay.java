package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class DestroyAllElementsOnAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
        for (int i = 0; i < cardInPlay.elementSpaces.length; i++) {
            DestroyAllElementsOnSpace(cardInPlay.elementSpaces[i], elementBag);
        }
    }

    public void DestroyAllElementsOnSpace(ElementSpace elementSpace, ElementBag elementBag) {

    }
}

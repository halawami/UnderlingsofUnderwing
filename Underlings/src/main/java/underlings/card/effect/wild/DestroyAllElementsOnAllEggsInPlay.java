package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;

public class DestroyAllElementsOnAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
        if (cardInPlay.isClaimed()) {
            for (int i = 0; i < cardInPlay.elementSpaces.length; i++) {
                cardInPlay.elementSpaces[i].destroyAllElements();
            }
        }
    }
}

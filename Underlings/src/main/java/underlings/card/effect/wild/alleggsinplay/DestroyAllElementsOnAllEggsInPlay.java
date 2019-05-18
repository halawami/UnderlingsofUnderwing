package underlings.card.effect.wild.alleggsinplay;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.utilities.LocaleWrap;

public class DestroyAllElementsOnAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
        if (cardInPlay.isClaimed()) {
            for (int i = 0; i < cardInPlay.elementSpaces.length; i++) {
                cardInPlay.elementSpaces[i].destroyAllElements();
            }
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.get("destroy_all_elements_on_all_eggs_effect");
    }
}

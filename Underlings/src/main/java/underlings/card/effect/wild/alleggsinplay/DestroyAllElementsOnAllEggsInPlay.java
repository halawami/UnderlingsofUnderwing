package underlings.card.effect.wild.alleggsinplay;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.handler.HandlerMovementLogic;
import underlings.utilities.ElementSpaceUtilities;
import underlings.utilities.LocaleUtilities;

public class DestroyAllElementsOnAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceUtilities elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
        if (cardInPlay.isClaimed()) {
            for (int i = 0; i < cardInPlay.elementSpaces.length; i++) {
                cardInPlay.elementSpaces[i].destroyAllElements();
            }
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("destroy_all_elements_on_all_eggs_effect");
    }
}

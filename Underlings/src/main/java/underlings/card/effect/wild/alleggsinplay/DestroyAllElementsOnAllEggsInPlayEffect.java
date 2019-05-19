package underlings.card.effect.wild.alleggsinplay;

import underlings.card.Card;
import underlings.card.effect.AllEggsInPlayEffect;
import underlings.utilities.LocaleUtilities;

public class DestroyAllElementsOnAllEggsInPlayEffect extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay) {
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

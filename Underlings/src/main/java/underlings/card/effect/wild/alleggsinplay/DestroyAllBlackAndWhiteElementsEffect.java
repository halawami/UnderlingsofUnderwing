package underlings.card.effect.wild.alleggsinplay;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.utilities.LocaleWrap;

public class DestroyAllBlackAndWhiteElementsEffect extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay) {
        for (int i = 0; i < cardInPlay.elementSpaces.length; i++) {
            cardInPlay.elementSpaces[i].destroyAllElementsOfColor(ElementColor.BLACK);
            cardInPlay.elementSpaces[i].destroyAllElementsOfColor(ElementColor.WHITE);
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.get("destroy_white_black_elements");
    }
}

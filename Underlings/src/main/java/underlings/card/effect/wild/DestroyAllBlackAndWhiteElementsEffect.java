package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;

public class DestroyAllBlackAndWhiteElementsEffect extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
        for (int i = 0; i < cardInPlay.elementSpaces.length; i++) {
            cardInPlay.elementSpaces[i].destroyAllElementsOfColor(ElementColor.BLACK);
            cardInPlay.elementSpaces[i].destroyAllElementsOfColor(ElementColor.WHITE);
        }
    }

}

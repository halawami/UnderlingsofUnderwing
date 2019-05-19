package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.wild.alleggsinplay.AllEggsInPlayEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerMovementLogic;
import underlings.utilities.LocaleWrap;

public class DestroyAllBlackAndWhiteElementsEffect extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
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

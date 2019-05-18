package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.wild.alleggsinplay.AllEggsInPlayEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.utilities.LocaleWrap;

public class RemoveAllHandlersFromAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
        if (cardInPlay.handler != null && cardInPlay.handler != WildHandler.getInstance()) {
            cardInPlay.handler.moveToState(HandlerState.BREAK_ROOM);
            cardInPlay.handler = null;
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.get("remove_all_handlers_from_eggs_effect");
    }
}

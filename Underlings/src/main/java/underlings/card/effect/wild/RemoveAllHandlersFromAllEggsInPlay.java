package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.wild.alleggsinplay.AllEggsInPlayEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleWrap;

public class RemoveAllHandlersFromAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
        handlerMovementLogic.move(cardInPlay.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(cardInPlay);
    }

    @Override
    public String toString() {
        return LocaleWrap.get("remove_all_handlers_from_eggs_effect");
    }
}

package underlings.card.effect.wild.alleggsinplay;

import underlings.card.Card;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleUtilities;

public class RemoveAllHandlersFromAllEggsInPlay extends AllEggsInPlayEffect {

    @Override
    public void applyOnCardInPlay(Card cardInPlay, HandlerMovementLogic handlerMovementLogic) {
        handlerMovementLogic.move(cardInPlay.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(cardInPlay);
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("remove_all_handlers_from_eggs_effect");
    }
}

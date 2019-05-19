package underlings.card.effect.wild.adjacenteggs;

import underlings.card.Card;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleWrap;

public class ReturnAllAdjacentHandlersEffect extends AdjacentEggsEffect {

    public void applyOnAdjacentEgg(Card adjacentEgg, HandlerMovementLogic handlerMovementLogic) {
        handlerMovementLogic.move(adjacentEgg.handler, HandlerChoice.READY_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentEgg);
    }

    @Override
    public String toString() {
        return LocaleWrap.get("return_adjacent_handlers");
    }
}

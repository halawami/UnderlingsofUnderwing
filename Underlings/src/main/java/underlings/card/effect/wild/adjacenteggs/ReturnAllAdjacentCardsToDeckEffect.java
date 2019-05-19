package underlings.card.effect.wild.adjacenteggs;

import underlings.card.Card;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleUtilities;

public class ReturnAllAdjacentCardsToDeckEffect extends AdjacentEggsEffect {

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, Deck deck, HandlerMovementLogic handlerMovementLogic,
            HatchingGround hatchingGround) {
        handlerMovementLogic.move(adjacentEgg.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentEgg);
        hatchingGround.replaceCard(adjacentEgg);
        deck.addCard(adjacentEgg, true);
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("return_adjacent_eggs_effect");
    }
}

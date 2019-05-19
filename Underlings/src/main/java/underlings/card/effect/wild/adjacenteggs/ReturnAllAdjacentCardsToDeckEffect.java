package underlings.card.effect.wild.adjacenteggs;

import underlings.card.Card;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.FakePlayer;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class ReturnAllAdjacentCardsToDeckEffect extends AdjacentEggsEffect {

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            EggHatchingLogic eggHatchingLogic, Deck deck, HandlerMovementLogic handlerMovementLogic,
            HatchingGround hatchingGround) {
        handlerMovementLogic.move(adjacentEgg.handler, HandlerChoice.BREAK_ROOM, FakePlayer.getInstance());
        handlerMovementLogic.removeHandlerFromCard(adjacentEgg);
        hatchingGround.replaceCard(adjacentEgg);
        deck.addCard(adjacentEgg, true);
    }

    @Override
    public String toString() {
        return LocaleWrap.get("return_adjacent_eggs_effect");
    }
}

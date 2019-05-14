package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.AdjacentEggsEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerMovementLogic;
import underlings.utilities.EggHatchingLogic;

public class ReturnAllAdjacentCardsToDeckEffect extends AdjacentEggsEffect {

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            EggHatchingLogic eggHatchingLogic, Deck deck, HandlerMovementLogic handlerMovementLogic,
            HatchingGround hatchingGround) {
        hatchingGround.replaceCard(adjacentEgg);
        deck.addCard(adjacentEgg);
    }
}

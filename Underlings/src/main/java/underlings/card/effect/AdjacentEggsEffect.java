package underlings.card.effect;

import java.util.List;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class AdjacentEggsEffect extends DiverseHatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            this.applyOnAdjacentEgg(adjacentCard, currentPlayer.elementSpaceLogic, elementBag);
            this.applyOnAdjacentEgg(adjacentCard, eggHatchingLogic);
            this.applyOnAdjacentEgg(adjacentCard, elementBag, currentPlayer.elementSpaceLogic, eggHatchingLogic, deck,
                    handlerMovementLogic, hatchingGround);
        }
    }

    public void applyOnAdjacentEgg(Card adjacentEgg, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
    }

    public void applyOnAdjacentEgg(Card adjacentEgg, EggHatchingLogic eggHatchingLogic) {
    }

    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic, HatchingGround hatchingGround) {
    }
}

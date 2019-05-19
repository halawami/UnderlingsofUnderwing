package underlings.card.effect;

import java.util.List;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementSpaceUtilities;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public abstract class AllEggsInPlayEffect extends Effect {

    @Override
    protected void apply(HatchingGround hatchingGround, ElementBag elementBag, Player currentPlayer,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> cardsInPlay = hatchingGround.getAllCards();
        for (Card cardInPlay : cardsInPlay) {
            this.applyOnCardInPlay(cardInPlay);
            this.applyOnCardInPlay(cardInPlay, handlerMovementLogic);
            this.applyOnCardInPlay(cardInPlay, currentPlayer.elementSpaceLogic, elementBag, handlerMovementLogic);
        }
    }

    public void applyOnCardInPlay(Card cardInPlay) {
    }

    public void applyOnCardInPlay(Card cardInPlay, HandlerMovementLogic handlerMovementLogic) {
    }

    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceUtilities elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
    }


}

package underlings.card.effect.wild.alleggsinplay;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DiverseHatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class AllEggsInPlayEffect extends DiverseHatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> cardsInPlay = hatchingGround.getAllCards();
        for (Card cardInPlay : cardsInPlay) {
            this.applyOnCardInPlay(cardInPlay);
            this.applyOnCardInPlay(cardInPlay, currentPlayer.elementSpaceLogic, elementBag, handlerMovementLogic);
        }
    }

    public void applyOnCardInPlay(Card cardInPlay) {
    }

    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
    }


}

package underlings.card.effect.wild.alleggsinplay;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DiverseHatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.ElementSpaceUtilities;

public abstract class AllEggsInPlayEffect extends DiverseHatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            Player currentPlayer, EggHatchingUtilities eggHatchingUtilities, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> cardsInPlay = hatchingGround.getAllCards();
        for (Card cardInPlay : cardsInPlay) {
            this.applyOnCardInPlay(cardInPlay);
            this.applyOnCardInPlay(cardInPlay, currentPlayer.elementSpaceLogic, elementBag, handlerMovementLogic);
        }
    }

    public void applyOnCardInPlay(Card cardInPlay) {
    }

    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceUtilities elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
    }


}

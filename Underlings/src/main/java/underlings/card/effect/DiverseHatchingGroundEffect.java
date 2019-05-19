package underlings.card.effect;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;

public abstract class DiverseHatchingGroundEffect extends Effect {

    @Override
    protected abstract void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            Player currentPlayer, EggHatchingUtilities eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic);
}

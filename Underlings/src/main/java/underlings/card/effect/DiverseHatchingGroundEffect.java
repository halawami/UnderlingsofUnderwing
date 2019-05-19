package underlings.card.effect;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class DiverseHatchingGroundEffect extends Effect {

    @Override
    protected abstract void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic);
}

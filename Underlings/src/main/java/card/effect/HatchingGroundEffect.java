package card.effect;

import card.Card;
import element.ElementBag;
import element.utilities.ElementSpaceLogic;
import game.HatchingGround;

public abstract class HatchingGroundEffect extends Effect {


    @Override
    protected abstract void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic);
}

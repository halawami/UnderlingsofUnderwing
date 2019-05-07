package underlings.card.effect;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;

public abstract class HatchingGroundEffect extends Effect {


    @Override
    protected abstract void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, Gui gui);
}

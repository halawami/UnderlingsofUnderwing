package underlings.card.effect;

import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;

public abstract class HatchingGroundEffect extends Effect {

    @Override
    protected abstract void apply(HatchingGround hatchingGround, Gui gui);
}

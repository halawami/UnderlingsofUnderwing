package underlings.card.effect;

import underlings.game.HatchingGround;
import underlings.gui.Gui;

public abstract class HatchingGroundEffect extends Effect {

    @Override
    protected abstract void apply(HatchingGround hatchingGround, Gui gui);
}

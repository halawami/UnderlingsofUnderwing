package underlings.card.effect.wild;

import underlings.card.effect.HatchingGroundEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.utilities.LocaleWrap;

public class AllEggsHatchLateEffect extends HatchingGroundEffect {

    @Override
    protected void apply(HatchingGround hatchingGround, Gui gui) {
        hatchingGround.lateHatching = true;
    }

    @Override
    public String toString() {
        return LocaleWrap.get("egg_hatches_late_effect");
    }
}

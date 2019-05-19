package underlings.card.effect.wild;

import underlings.card.effect.HatchingGroundEffect;
import underlings.hatchingground.HatchingGround;
import underlings.utilities.LocaleUtilities;

public class AllEggsHatchLateEffect extends HatchingGroundEffect {

    @Override
    protected void apply(HatchingGround hatchingGround) {
        hatchingGround.lateHatching = true;
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("egg_hatches_late_effect");
    }
}

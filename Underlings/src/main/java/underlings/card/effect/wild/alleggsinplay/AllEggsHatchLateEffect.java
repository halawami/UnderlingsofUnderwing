package underlings.card.effect.wild.alleggsinplay;

import underlings.card.effect.HatchingGroundEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.utilities.LocaleWrap;

public class AllEggsHatchLateEffect extends HatchingGroundEffect {

    @Override
    protected void apply(HatchingGround hatchingGround, Gui gui) {
        hatchingGround.lateHatching = true;
        gui.alert(LocaleWrap.get("eggs_hatch_late"), PromptType.WARNING);
    }

}

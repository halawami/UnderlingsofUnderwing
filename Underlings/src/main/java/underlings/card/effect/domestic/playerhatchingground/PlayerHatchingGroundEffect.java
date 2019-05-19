package underlings.card.effect.domestic.playerhatchingground;

import underlings.card.effect.Effect;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;

public abstract class PlayerHatchingGroundEffect extends Effect {

    @Override
    protected abstract void apply(HatchingGround hatchingGround, EggHatchingUtilities hatchingLogic, Player currentPlayer,
            Gui gui);


}

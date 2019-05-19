package underlings.card.effect.domestic;

import underlings.card.effect.Effect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class PlayerHatchingGroundEffect extends Effect {

    @Override
    protected abstract void apply(HatchingGround hatchingGround, EggHatchingLogic hatchingLogic, Player currentPlayer,
            Gui gui);


}

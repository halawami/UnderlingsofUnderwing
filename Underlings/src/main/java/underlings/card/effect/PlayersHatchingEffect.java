package underlings.card.effect;

import java.util.List;

import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class PlayersHatchingEffect extends Effect {

    protected abstract void apply(Player currentPlayer, List<Player> players, Gui gui,
            EggHatchingLogic eggHatchingLogic);
}

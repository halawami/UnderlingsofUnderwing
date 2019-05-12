package underlings.card.effect;

import java.util.List;

import underlings.gui.Gui;
import underlings.player.Player;

public abstract class PlayersEffect extends Effect {

    protected abstract void apply(Player currentPlayer, List<Player> players, Gui gui);
}

package underlings.card.effect.domestic;

import java.util.List;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class StealAllStoredElements extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        Player selectedPlayer = gui.promptChoice(LocaleWrap.get("prompt_war_players"), players, currentPlayer.id);
        currentPlayer.stealAllElementsFromPlayer(selectedPlayer);
    }
}

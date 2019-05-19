package underlings.card.effect.domestic.players;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class StealAllStoredElements extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        Player selectedPlayer = gui.promptChoice(LocaleUtilities.get("prompt_war_players"), players, currentPlayer.id);
        currentPlayer.stealAllElementsFromPlayer(selectedPlayer);
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("take_all_elements_effect");
    }
}

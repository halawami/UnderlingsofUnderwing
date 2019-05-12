package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class StealAllStoredElements extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        Player selectedPlayer = gui.promptPlayer("Choose a player to take all stored elements from",
                currentPlayer.getPlayerId(),
                players);
        currentPlayer.stealAllElementsFromPlayer(selectedPlayer);
    }
}

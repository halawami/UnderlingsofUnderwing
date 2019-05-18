package underlings.card.effect.wild;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class DestroyAllPlayersStoredElements extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        players.forEach(Player::destroyAllElements);
    }

    @Override
    public String toString() {
        return LocaleWrap.get("destroy_all_players_stored_elemetns");
    }
}

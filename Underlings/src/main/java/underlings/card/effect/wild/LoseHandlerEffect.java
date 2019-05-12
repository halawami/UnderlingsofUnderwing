package underlings.card.effect.wild;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class LoseHandlerEffect extends PlayersEffect {

    @Override
    protected void apply(List<Player> players, Gui gui) {
        for (int i = 0; i < 2; i++) {
            players.get(i).loseHandler();
        }
    }
}

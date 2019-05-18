package underlings.card.effect.wild;

import java.util.List;

import underlings.card.effect.ObserverEffect;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class DrawOneLessElementEffect extends PlayersEffect implements ObserverEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        players.forEach(player -> player.addObserverEffect(this));
    }

    @Override
    public void onPhaseOne(Player player) {

    }

}

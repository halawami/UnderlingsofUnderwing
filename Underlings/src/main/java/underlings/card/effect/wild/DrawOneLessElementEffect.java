package underlings.card.effect.wild;

import java.util.ArrayList;
import java.util.List;

import underlings.card.effect.ObserverEffect;
import underlings.card.effect.PlayersEffect;
import underlings.element.ElementGiver;
import underlings.gui.Gui;
import underlings.player.Player;

public class DrawOneLessElementEffect extends PlayersEffect implements ObserverEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        players.forEach(player -> player.addObserverEffect(this));
    }

    @Override
    public void onPhaseOne(Player player) {
        player.effectElementGivers = this.getEffectElementGivers(player.getElementGivers());
        player.useEffectElementGivers(true);
    }

    public List<ElementGiver> getEffectElementGivers(List<ElementGiver> elementGivers) {
        return new ArrayList<>();
    }

}

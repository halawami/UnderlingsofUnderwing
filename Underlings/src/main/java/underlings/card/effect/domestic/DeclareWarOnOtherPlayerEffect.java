package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.element.Element;
import underlings.gui.Gui;
import underlings.player.Player;

public class DeclareWarOnOtherPlayerEffect extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        players.remove(currentPlayer);
        gui.promptPlayerToDeclareWarOn(players, currentPlayer.getPlayerId());
        for (Element elements : players.get(0).elements) {
            currentPlayer.elements.add(elements);
        }
    }

}

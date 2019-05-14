package underlings.card.effect.wild;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.element.ElementColor;
import underlings.gui.Gui;
import underlings.player.Player;

public class RemoveBlackRecipes extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        for (Player player : players) {
            player.elementSpaceLogic.resetRecipes(ElementColor.BLACK);
        }
    }

}

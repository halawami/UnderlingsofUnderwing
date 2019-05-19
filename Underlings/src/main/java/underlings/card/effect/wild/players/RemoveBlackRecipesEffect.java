package underlings.card.effect.wild.players;

import java.util.List;

import underlings.card.effect.PlayersEffect;
import underlings.element.ElementColor;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class RemoveBlackRecipesEffect extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        for (Player player : players) {
            player.elementSpaceLogic.resetRecipes(ElementColor.BLACK);
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("remove_black_recipes_effect");
    }
}

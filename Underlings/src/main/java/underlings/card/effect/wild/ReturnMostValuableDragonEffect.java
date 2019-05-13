package underlings.card.effect.wild;

import java.util.List;

import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;

public class ReturnMostValuableDragonEffect extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        players.forEach(player -> this.returnMostValuableDragon(player, deck, gui));
    }

    public void returnMostValuableDragon(Player player, Deck deck, Gui gui) {
        player.getMostValuableDragons();

    }
}

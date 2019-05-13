package underlings.card.effect.wild;

import java.util.List;

import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;

public class ReturnMostValuableDragonEffect extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        for (int i = 0; i < 2; i++) {
            this.returnMostValueDragon(players, deck, gui);
        }
    }

    public void returnMostValueDragon(List<Player> players, Deck deck, Gui gui) {

    }
}

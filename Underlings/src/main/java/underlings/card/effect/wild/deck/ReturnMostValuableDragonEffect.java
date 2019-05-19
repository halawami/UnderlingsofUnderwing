package underlings.card.effect.wild.deck;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ReturnMostValuableDragonEffect extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        players.forEach(player -> this.returnMostValuableDragon(player, deck, gui));
    }

    public void returnMostValuableDragon(Player player, Deck deck, Gui gui) {
        List<Card> mostValuableDragons = player.getMostValuableDragons();
        if (!mostValuableDragons.isEmpty()) {
            Card mostValuableDragon =
                    gui.promptChoice("Pick a dragon to return to deck", mostValuableDragons, player.id);
            player.hatchedCards.remove(mostValuableDragon);
            deck.addCard(mostValuableDragon, true);
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.get("return_most_valuable_dragon_effect");
    }
}

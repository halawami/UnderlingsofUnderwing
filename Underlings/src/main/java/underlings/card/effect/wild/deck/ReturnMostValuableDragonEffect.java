package underlings.card.effect.wild.deck;

import java.util.List;
import underlings.card.Card;
import underlings.card.effect.DeckEffects;
import underlings.gui.Gui;
import underlings.hatchingground.Deck;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class ReturnMostValuableDragonEffect extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        players.forEach(player -> this.returnMostValuableDragon(player, deck, gui));
    }

    public void returnMostValuableDragon(Player player, Deck deck, Gui gui) {
        List<Card> mostValuableDragons = player.getMostValuableDragons();
        if (!mostValuableDragons.isEmpty()) {
            Card mostValuableDragon =
                    gui.promptChoice(LocaleUtilities.get("prompt_dragon_return_deck"), mostValuableDragons, player.id);
            player.hatchedCards.remove(mostValuableDragon);
            deck.addCard(mostValuableDragon, true);
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("return_most_valuable_dragon_effect");
    }
}

package underlings.card.effect.wild.deck;

import java.util.Arrays;
import java.util.List;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.DeckEffects;
import underlings.gui.Gui;
import underlings.hatchingground.Deck;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class ReturnAllHatchedDragonsFromPlayersEffect extends DeckEffects {

    public Temperature[] temperatures;

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        List<Temperature> temperaturesList = Arrays.asList(this.temperatures);
        for (Player player : players) {
            this.removeCardsOfTemperature(deck, temperaturesList, player);
        }
    }

    public void removeCardsOfTemperature(Deck deck, List<Temperature> temperaturesList, Player player) {
        for (int i = 0; i < player.hatchedCards.size(); i++) {
            Card card = player.hatchedCards.get(i);
            if (temperaturesList.contains(card.temperature)) {
                deck.addCard(card, true);
                player.hatchedCards.remove(i--);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder temperature = new StringBuilder();
        for (Temperature temp : this.temperatures) {
            temperature.append(temp);
            temperature.append(" ");
        }
        return LocaleUtilities.format("return_hatched_dragons", temperature);
    }
}

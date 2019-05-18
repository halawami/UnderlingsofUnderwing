package underlings.card.effect.wild;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ReturnAllHatchedDragonsFromPlayers extends DeckEffects {

    public Temperature[] temperatures;

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        List<Temperature> temperaturesList = Arrays.asList(this.temperatures);
        for (Player player : players) {
            List<Card> cardsToRemove = new LinkedList<>();
            for (Card card : player.hatchedCards) {
                if (temperaturesList.contains(card.temperature)) {
                    deck.addCard(card, true);
                    cardsToRemove.add(card);
                }
            }
            player.hatchedCards.removeAll(cardsToRemove);
        }
    }

    @Override
    public String toString() {
        StringBuilder temperature = new StringBuilder();
        for (Temperature temp : this.temperatures) {
            temperature.append(temp);
            temperature.append(" ");
        }
        return LocaleWrap.format("return_hatched_dragons", temperature);
    }
}

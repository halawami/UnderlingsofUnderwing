package underlings.card.effect.wild;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.player.Player;

public class ReturnAllHatchedDragonsFromPlayers extends DeckEffects {
    public Temperature[] temperatures;

    @Override
    protected void apply(List<Player> players, Deck deck) {
        List<Temperature> temperaturesList = Arrays.asList(temperatures);
        for (Player player : players) {
            List<Card> cardsToRemove = new LinkedList<>();
            for (Card card : player.hatchedCards) {
                if (temperaturesList.contains(card.temperature)) {
                    deck.addCard(card);
                    cardsToRemove.add(card);
                }
            }
            player.hatchedCards.removeAll(cardsToRemove);
        }
    }

}

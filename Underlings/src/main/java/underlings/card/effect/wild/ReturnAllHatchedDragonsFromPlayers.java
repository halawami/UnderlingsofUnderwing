package underlings.card.effect.wild;

import java.util.LinkedList;
import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.player.Player;

public class ReturnAllHatchedDragonsFromPlayers extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck) {
        for (Player player : players) {
            List<Card> cardsToRemove = new LinkedList<>();
            for (Card card : player.hatchedCards) {
                deck.addCard(card);
                cardsToRemove.add(card);
            }
            player.hatchedCards.removeAll(cardsToRemove);
        }
    }

}

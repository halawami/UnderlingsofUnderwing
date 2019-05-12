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
        List<Card> cardsToRemove = new LinkedList<>();
        for (Card card : players.get(0).hatchedCards) {
            deck.addCard(card);
            cardsToRemove.add(card);
        }
        players.get(0).hatchedCards.removeAll(cardsToRemove);
    }

}

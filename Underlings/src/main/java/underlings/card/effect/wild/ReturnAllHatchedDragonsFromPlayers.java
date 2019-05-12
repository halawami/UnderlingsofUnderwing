package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.player.Player;

public class ReturnAllHatchedDragonsFromPlayers extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck) {
        Card card = players.get(0).hatchedCards.get(0);
        players.get(0).hatchedCards.remove(card);
        deck.addCard(card);
    }

}

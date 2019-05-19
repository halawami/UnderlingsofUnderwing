package underlings.card.effect.domestic.deck;

import java.util.ArrayList;
import java.util.List;

import underlings.card.Card;
import underlings.card.effect.DeckEffects;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;

public class ReorderTopThreeCardsEffect extends DeckEffects {

    @Override
    protected void apply(List<Player> players, Deck deck, Gui gui) {
        List<Card> topThreeCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            topThreeCards.add(deck.draw());
        }

        List<Card> reorderedCards = gui.reorderCards(topThreeCards);
        for (Card card : reorderedCards) {
            deck.addCard(card, false);
        }
    }
}

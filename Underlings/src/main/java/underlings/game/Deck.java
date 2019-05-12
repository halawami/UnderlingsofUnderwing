package underlings.game;

import java.util.List;
import java.util.Stack;

import underlings.card.Card;
import underlings.card.EmptyCard;

public class Deck {

    private Stack<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return EmptyCard.getInstance();
        }
        return cards.pop();
    }

    public int getSize() {
        return this.cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
        // TODO: shuffle here
    }

}

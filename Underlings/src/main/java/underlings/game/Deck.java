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
        if (card != EmptyCard.getInstance()) {
            this.cards.add(card);
        }
        // TODO: shuffle here
        // TODO: ^^ don't shuffle here, but instead make a shuffle function and call it separately, because of 'Lusura'
    }

}

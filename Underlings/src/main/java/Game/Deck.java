package Game;

import java.util.List;
import java.util.Stack;

public class Deck {

    Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = cards;

    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Can't draw from an empty deck");
        }
        return cards.pop();
    }

    public int getSize() {
        return 50;
    }
}

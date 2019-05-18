package underlings.game;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import underlings.card.Card;
import underlings.card.EmptyCard;

public class Deck {

    protected List<Card> cards;

    @SuppressWarnings("rawtypes")
    Consumer<List> shuffleFunction;

    public Deck(List<Card> cards, Consumer<List> shuffleFunction) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
        this.shuffleFunction = shuffleFunction;
    }

    public Card draw() {
        if (this.cards.isEmpty()) {
            return EmptyCard.getInstance();
        }
        return this.cards.remove(0);
    }

    public void addCard(Card card, boolean shuffle) {
        if (card != EmptyCard.getInstance()) {
            this.cards.add(card);
        }

        if (shuffle) {
            this.shuffleFunction.accept(this.cards);
        }
    }



}

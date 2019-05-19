package underlings.hatchingground;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import underlings.card.Card;
import underlings.card.EmptyCard;

@SuppressWarnings("rawtypes")
public class Deck {

    public List<Card> cards;

    Consumer<List> shuffleFunction;

    public Deck(List<Card> cards, Consumer<List> shuffleFunction) {
        this.cards = new ArrayList<>(cards);
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
            this.shuffle();
        }
    }

    public void shuffle() {
        this.shuffleFunction.accept(this.cards);
    }



}

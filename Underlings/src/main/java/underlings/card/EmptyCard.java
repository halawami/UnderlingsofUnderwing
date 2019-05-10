package underlings.card;

public class EmptyCard extends Card {

    private static EmptyCard instance = new EmptyCard();

    private EmptyCard() {

    }

    public static EmptyCard getInstance() {
        return instance;
    }
}

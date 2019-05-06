package underlings.card;

public class EmptyCard extends Card {

    private static EmptyCard instance;

    private EmptyCard() {
        super();
    }

    public static EmptyCard getInstance() {
        if (instance == null) {
            instance = new EmptyCard();
        }
        return instance;
    }
}

package underlings.card;

import underlings.card.effect.Effect;
import underlings.element.ElementSpace;

public class EmptyCard extends Card {

    private static EmptyCard instance = new EmptyCard();

    private EmptyCard() {
        domesticEffects = new Effect[1];
        wildEffects = new Effect[1];
        elementSpaces = new ElementSpace[1];
    }

    public static EmptyCard getInstance() {
        return instance;
    }
}

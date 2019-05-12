package underlings;

import java.util.List;
import java.util.Random;
import underlings.card.Card;
import underlings.gui.ConcretePrompt;

public class TestPrompt extends ConcretePrompt {
    Random rand;
    long delay;

    public TestPrompt() {
        this(10);
    }

    public TestPrompt(long delay) {
        this.rand = new Random();
        this.delay = delay;
    }

    public void delay() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {
        this.delay();
        return choices.get(this.rand.nextInt(choices.size()));
    }

    @Override
    public boolean promptDecision(String question, int playerId) {
        this.delay();
        return this.rand.nextBoolean();
    }

    @Override
    public int promptInt(String prompt, int min, int max) {
        this.delay();
        int val = 0;
        do {
            val = this.rand.nextInt(max);
        } while (val < min || val > max);
        return val;
    }

    @Override
    public void displayMessage(String message, int playerId, int icon) {
        this.delay();
        if (!(message.contains("placements") || message.contains("incubation") || message.contains("applied"))) {
            super.displayMessage(message, playerId, icon);
        }
    }

    @Override
    public Card pickCard(String prompt, Card[][] cards, int playerId) {
        this.delay();
        Card card = null;
        while (card == null) {
            int i = this.rand.nextInt(cards.length);
            int j = this.rand.nextInt(cards[i].length);
            card = cards[i][j];
        }
        return card;
    }
}

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
        System.out.println(choices);
        T val = choices.get(this.rand.nextInt(choices.size()));
        System.out.println(prompt + " choices: " + choices + " (return " + val + ")");
        return val;
    }

    @Override
    public boolean promptDecision(String question, int playerId) {
        this.delay();
        boolean val = this.rand.nextBoolean();
        System.out.println(question + " (return " + val + ")");
        return val;
    }

    @Override
    public int promptInt(String prompt, int min, int max) {
        this.delay();
        int val = 0;
        do {
            val = this.rand.nextInt(max);
        } while (val < min || val > max);
        System.out.println(prompt + " (return " + val + ")");
        return val;
    }

    @Override
    public void displayMessage(String message, int playerId, int icon) {
        this.delay();
        System.out.println(playerId + ": " + message);
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
        System.out.println("\n" + playerId + ": " + prompt);
        this.printCards(cards);
        System.out.println("picked " + card + "\n");
        return card;
    }

    private void printCards(Card[][] cards) {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[0].length; j++) {
                System.out.print(cards[i][j]);
            }
            System.out.println();
        }
    }
}
